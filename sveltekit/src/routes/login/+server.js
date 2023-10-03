import { CLIENT_ID, REDIRECT_URI } from '$env/static/private'

export function GET({ url }) {
    let redirectURL = new URL('http://localhost:8080/oauth2/authorize')
    const state = '123'

    redirectURL.searchParams.set('response_type', 'code')
    redirectURL.searchParams.set('client_id', CLIENT_ID)
    redirectURL.searchParams.set('redirect_uri', REDIRECT_URI)
    redirectURL.searchParams.set('scope', ['email', 'todos.read'].join(' '))
    redirectURL.searchParams.set('state', state)
    
    // why no Response.redirect? because we want to change headers
    // later and Response.redirect headers are immutable
    const response = new Response(null, {
        status: 302,
        headers: {
            'Location': redirectURL.toString()
        }
    })

    return response
}