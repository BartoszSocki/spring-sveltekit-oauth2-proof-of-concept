import { CLIENT_ID, REDIRECT_URI } from '$env/static/private'
import { generateAndSaveStateForSession } from '$lib/server/SessionManagement'
import { redirect } from '@sveltejs/kit'

export async function GET({ url, cookies }) {
    let redirectURL = new URL('http://localhost:8080/oauth2/authorize')
    const sessionId = cookies.get('sessionid')

    // what will happen if session is missing or session is not existing?

    console.log(sessionId)

    const state = await generateAndSaveStateForSession(sessionId)

    redirectURL.searchParams.set('response_type', 'code')
    redirectURL.searchParams.set('client_id', CLIENT_ID)
    redirectURL.searchParams.set('redirect_uri', REDIRECT_URI)
    redirectURL.searchParams.set('scope', ['test.read'].join(' '))
    redirectURL.searchParams.set('state', state)

    throw redirect(302, redirectURL)
}