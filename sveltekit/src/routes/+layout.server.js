import { getSessionStatus, isUserAuthed } from '$lib/SessionManagement/index.js'

export async function load({ cookies }) {
    const sessionId = cookies.get('sessionid')
    
    return {
        isUserAuthenticated: isUserAuthed(sessionId),
        sessionStatus: getSessionStatus(sessionId)
    }
}
