import { newAnonymousSession } from '$lib/SessionManagement';
import { PROD } from '$env/static/private'
import { logger } from '$lib/Logger'

// creates session when session is missing
export async function anonymousSessionHandler({ event, resolve }) {
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