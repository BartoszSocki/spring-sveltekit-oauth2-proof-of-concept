import { newAnonymousSession } from '$lib/SessionManagement';
import { PROD } from '$env/static/private'
import { sequence } from '@sveltejs/kit/hooks';
import { Session } from '$lib/db';
import { SessionStatus, getSessionStatus, renewSession, isSessionAnonymous } from './lib/SessionManagement';
import { logger } from '$lib/Logger'
import { findUserById } from '$lib/UserService';

// invalidates session
async function invalidateSessionHandler({ event, resolve }) {
    const sessionId = event.cookies.get('sessionid')

    if (sessionId === undefined) {
        return await resolve(event)
    }

    if (!event.url.pathname.startsWith('/logout')) {
        return await resolve(event)
    }

    logger.debug('invoked invalidateSessionHandler')

    let headers = new Headers()
    headers.append('Location', '/')
    headers.append('Set-Cookie', `sessionid= ; Max-Age=0`)

    const response = new Response(null, {
        status: 302,
        headers
    })
    
    const session = await Session.findByPk(sessionId)
    await session.destroy()

    return response
}

// renew idle session if possible
async function idleSessionHandler({ event, resolve }) {
    logger.debug('invoked idleSessionHandler')
    const sessionId = event.cookies.get('sessionid')

    // continue anonymousSessionHandler will take care of it
    if (sessionId === undefined) {
        return await resolve(event)
    }

    try {
        const newSession = await renewSession(sessionId)
        logger.info(`session renewal ${sessionId} -> ${newSession.id}`)

        event.cookies.set('sessionid', newSession.id, {
            expires: newSession.expirationDateIdle,
            httpOnly: PROD !== undefined && PROD === 'true'
        })
    } catch(e) {
        logger.debug(`session renewal attempt failed with message: ${e.message}`)
    }

    const response = await resolve(event)
    return response
}

// creates session when session is missing
async function anonymousSessionHandler({ event, resolve }) {
    logger.debug('invoked anonymousSessionHandler')
    const sessionId = event.cookies.get('sessionid')

    if (sessionId !== undefined) {
        return await resolve(event)
    }

    let ipAddress
    
    try {
        ipAddress = event.getClientAddress()
    } catch(e) {
        ipAddress = null
    }

    const session = await newAnonymousSession({ ipAddress })

    event.cookies.set('sessionid', session.id, {
        expires: session.expirationDateIdle,
        httpOnly: PROD !== undefined && PROD === 'true'
    })

    const response = await resolve(event)
    return response
}

// when session is active, it adds access_token to api requests
export async function handleFetch({ request, event, fetch }) {
    logger.debug(`intercepted fetch request to ${request.url.toString()}`)
    const sessionId = event.cookies.get('sessionid')

    const sessionStatus = await getSessionStatus(sessionId)
    const isSessionActive = sessionStatus === SessionStatus.Active
    const isUserAnonymous = await isSessionAnonymous(sessionId)

    if (isSessionActive && !isUserAnonymous) {
        const session = await Session.findByPk(sessionId)
        const user = await findUserById(session.userId)
        const accessToken = user.accessToken

        logger.debug('adding access token to API request')
        request.headers.set('Authorization', `Bearer ${accessToken}`)
    }

    return fetch(request)
}

export const handle = sequence(
    invalidateSessionHandler,
    idleSessionHandler,
    anonymousSessionHandler
)