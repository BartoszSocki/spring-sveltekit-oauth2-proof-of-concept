import { CLIENT_ID, CLIENT_SECRET, PROD } from "$env/static/private"
import jwtDecode from "jwt-decode"
import { User, Session } from '$lib/db.js'

export async function GET({ cookies, url }) {
    const code = url.searchParams.get("code")
    const sessionId = cookies.get('sessionid')
    const redirectURL = new URL("http://localhost:3000")

    let response = null

    try {
        response = await getAccessToken(code)
        console.log(response)
    } catch (e) {
        redirectURL.searchParams.set("success", "false")
        return Response.redirect(redirectURL)
    }
    
    const accessToken = response['access_token']
    const refreshToken = response['refresh_token']

    // 1. save user in db
    const email = jwtDecode(accessToken)['sub']
    const user = await saveUserIfNotPresent({ email, refreshToken, accessToken })

    // 2. change session id and log it
    const session = await Session.findByPk(sessionId)
    

    redirectURL.searchParams.set("success", "true")
    return Response.redirect(redirectURL)
}

async function getAccessToken(code) {
    const basicAuth = btoa(`${CLIENT_ID}:${CLIENT_SECRET}`)

    const url = new URL("http://localhost:8080/oauth2/token")
    url.searchParams.set("grant_type", "authorization_code")
    url.searchParams.set("code", code)
    url.searchParams.set("redirect_uri", "http://localhost:3000/login/oauth2/code/client")

    const res = await fetch(url, {
        method: "POST",
        headers: {
            "Authorization": `Basic ${basicAuth}`
        }
    })

    const json = await res.json()
    return json
}

async function saveUserIfNotPresent({ email, accessToken, refreshToken }) {
    const possibleUsers = await User.findAll({ where: { email } })
    console.log(possibleUsers)

    if (possibleUsers !== null && possibleUsers.length !== 0) {
        return possibleUsers.at(0)
    }

    const user = User.create({
        email,
        accessToken,
        refreshToken
    }, {
       skip: [PROD !== undefined && PROD === 'true' ? '' : 'email'] 
    })
    
    return user
}