import { newAnonymousSession } from '$lib/SessionManagement';
import { PROD } from '$env/static/private'
import { sequence } from '@sveltejs/kit/hooks';
import { Session } from '$lib/db';
import { renewSession } from './lib/SessionManagement';
import { debug } from '$lib/Logger'

// invalidates session
async function invalidateSessionHandler({ event, resolve }) {
    const sessionId = event.cookies.get('sessionid')

    if (sessionId === undefined) {
        return await resolve(event)
    }

    if (!event.url.pathname.startsWith('/logout')) {
        return await resolve(event)
    }

    debug('invoked invalidateSessionHandler')

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
    debug('invoked idleSessionHandler')
    const sessionId = event.cookies.get('sessionid')

    // continue anonymousSessionHandler will take care of it
    if (sessionId === undefined) {
        return await resolve(event)
    }

    try {
        debug(`trying to renew session: ${sessionId}`)
        const newSession = await renewSession(sessionId)
        debug(`session renewal successful ${sessionId} -> ${newSession.id}`)

        event.cookies.set('sessionid', newSession.id, {
            expires: newSession.expirationDateIdle,
            httpOnly: PROD !== undefined && PROD === 'true'
        })
    } catch(e) {
        debug(`attempt failed with message: ${e.message}`)
    }

    const response = await resolve(event)
    return response
}

// creates session when session is missing
async function anonymousSessionHandler({ event, resolve }) {
    debug('invoked anonymousSessionHandler')
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

    // TODO: start login flow

    const response = await resolve(event)
    return response
}

new Response(null, {
    
})

// when session is active, it adds access_token to api requests
async function activeSessionConsumer({ event, resolve }) {
    debug('invoked activeSessionConsumer')
    const sessionId = event.cookies.get('sessionid')

    const response = await resolve(event)
    return response
}

export const handle = sequence(
    invalidateSessionHandler,
    idleSessionHandler,
    anonymousSessionHandler,
    activeSessionConsumer
)