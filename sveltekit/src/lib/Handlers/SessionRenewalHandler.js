import { PROD } from '$env/static/private'
import { renewSession } from '$lib/SessionManagement';
import { logger } from '$lib/Logger'

// renew idle session if possible
export async function sessionRenewalHandler({ event, resolve }) {
    logger.debug('invoked idleSessionHandler')
    const sessionId = event.cookies.get('sessionid')

    // continue anonymousSessionHandler will take care of it
    if (sessionId === undefined || sessionId === null) {
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