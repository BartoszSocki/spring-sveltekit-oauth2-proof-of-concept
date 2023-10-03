import { CLIENT_ID, REDIRECT_URI } from "$env/static/private"

export function GET({ url }) {
    
    let redirectURL = new URL("http://localhost:8080/oauth2/authorize")
    redirectURL.searchParams.set("response_type", "code")
    redirectURL.searchParams.set("client_id", CLIENT_ID)
    redirectURL.searchParams.set("redirect_uri", REDIRECT_URI)
    redirectURL.searchParams.set('scope', ['email', 'todos.read'].join(' '))
    // redirectURL.searchParams.set("scope", "profile")
    // redirectURL.searchParams.set("scope", "openid")
    // redirectURL.searchParams.set("scope", "todos.read")
    
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