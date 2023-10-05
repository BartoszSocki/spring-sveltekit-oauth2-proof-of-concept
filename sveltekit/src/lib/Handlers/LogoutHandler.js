import { invalidateSession, findSessionById } from '$lib/SessionManagement';
import { logger } from '$lib/Logger'

export async function logoutHandler({ event, resolve }) {
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
    
    await invalidateSession(sessionId)

    return response
}