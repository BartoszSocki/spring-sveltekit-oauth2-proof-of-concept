import { CLIENT_ID, CLIENT_SECRET } from '$env/static/private'
import jwtDecode from 'jwt-decode'
import { logger } from '$lib/server/Logger'
import { isStateParameterValidForSession, rotateSession } from '$lib/server/SessionManagement'
import { createUser, findUserByEmail } from '$lib/server/UserService'

export async function GET({ cookies, url }) {
    const code = url.searchParams.get('code')
    const sessionId = cookies.get('sessionid')
    const redirectURL = new URL('http://localhost:3000')
    const state = url.searchParams.get('state')

    const isStateCorrect = await isStateParameterValidForSession(sessionId, state)

    if (!isStateCorrect) {
        logger.debug('oauth2 states are not the same redirecting to home route with error')

        redirectURL.searchParams.set('success', 'false')
        redirectURL.searchParams.set('error', 'states_missmatch')
        return Response.redirect(redirectURL)
    } 

    let response = null

    try {
        response = await getAccessToken(code)
        console.log(response)
    } catch (e) {
        logger.debug('failed to retrieve access token')

        redirectURL.searchParams.set('success', 'false')
        return Response.redirect(redirectURL)
    }
    
    const accessToken = response['access_token']
    const refreshToken = response['refresh_token']

    // 1. save user in db
    const email = jwtDecode(accessToken)['sub']
    const user = await saveUserIfNotPresent({ email, refreshToken, accessToken })

    // 2. rotate session
    const session = await rotateSession({ oldSessionId: sessionId, userId: user.id })
    logger.info(`user logged in, user: ${user.id}, session rotation: ${sessionId} -> ${session.id}`)
    // const session = await Session.findByPk(sessionId)

    redirectURL.searchParams.set('success', 'true')
    const headers = new Headers()
    headers.append('Location', redirectURL.toString())
    headers.append('Set-Cookie', `sessionid=${session.id}; expires=${session.expirationDateIdle.toUTCString()}; Path=/`)

    const redirect = new Response(null, {
        status: 302,
        headers
    })
    
    return redirect
}

async function getAccessToken(code) {
    const basicAuth = btoa(`${CLIENT_ID}:${CLIENT_SECRET}`)

    const url = new URL('http://localhost:8080/oauth2/token')
    url.searchParams.set('grant_type', 'authorization_code')
    url.searchParams.set('code', code)
    url.searchParams.set('redirect_uri', 'http://localhost:3000/login/oauth2/code/client')

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: {
                'Authorization': `Basic ${basicAuth}`
            }
        })

        const json = await res.json()
        return json
    } catch (e) {
        logger.warn(`code exchange failed with message: ${e.toString()}`)
        return null
    }
}

async function saveUserIfNotPresent({ email, accessToken, refreshToken }) {
    const possibleUser = await findUserByEmail({ email })

    if (possibleUser !== null) {
        logger.debug(`user with email: ${possibleUser.email} already exists`)
        return possibleUser
    }

    const user = await createUser({
        email, accessToken, refreshToken
    })

    logger.debug(`created new user email: ${user.email}, id: ${user.id}`)
    return user
}