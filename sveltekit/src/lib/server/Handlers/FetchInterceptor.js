import { SessionStatus, getSessionStatus, isSessionAnonymous, findSessionById } from '$lib/server/SessionManagement';
import { findUserById } from '$lib/server/UserService';
import { logger } from '$lib/server/Logger'

// when session is active, it adds access_token to api requests
export async function fetchInterceptor({ request, event, fetch }) {
    logger.debug(`intercepted fetch request to ${request.url.toString()}`)
    const sessionId = event.cookies.get('sessionid')

    const sessionStatus = await getSessionStatus(sessionId)
    const isSessionActive = sessionStatus === SessionStatus.Active
    const isUserAnonymous = await isSessionAnonymous(sessionId)

    if (isSessionActive && !isUserAnonymous) {
        const session = await findSessionById(sessionId)
        const user = await findUserById({ id: session.userId })
        const accessToken = user.accessToken

        logger.debug('adding access token to API request')
        request.headers.set('Authorization', `Bearer ${accessToken}`)
    }

    return fetch(request)
}