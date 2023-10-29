import { getSessionStatus, isUserAuthed } from '$lib/server/SessionManagement'

export async function load({ cookies }) {
    const sessionId = cookies.get('sessionid')
    
    return {
        isUserAuthenticated: isUserAuthed(sessionId),
        sessionStatus: getSessionStatus(sessionId)
    }
}
