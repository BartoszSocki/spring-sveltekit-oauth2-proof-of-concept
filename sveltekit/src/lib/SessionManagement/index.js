import { Session } from "$lib/db";
import { getRandomValues, randomUUID } from 'crypto'
import dayjs from "dayjs";
import { anonymousUserId } from "$lib/db";
import { debug } from "$lib/Logger";

const alpha = 'abcdefghijklkmnopqrstuvwxyz12345ABCDEFGHIJKLKMNOPQRSTUVWXYZ67890'

function generate32CharSessionId() {
    const entropy = new Uint8Array(32)
    getRandomValues(entropy)

    return Array.from(entropy).map(i => i >> 2).map(i => alpha.charAt(i)).join('')
}

function canSessionBeRenewed(session) {
    const now = Date.now()
    const expirationDate = session.expirationDate
    const expirationDateIdle = session.expirationDateIdle

    return expirationDate <= now && now < expirationDateIdle
}

export async function newAnonymousSession({ ipAddress }) {
    const sessionId = generate32CharSessionId()
    const now = dayjs()
    // const expirationDate = now.add(1, 'hour')
    const expirationDate = now;
    const expirationDateIdle = now.add(2, 'hour')

    const session = await Session.create({
        id: sessionId,
        ipAddress,
        userId: anonymousUserId,
        expirationDate,
        expirationDateIdle
    })

    return session
}

export async function doesSessionExists(sessionId) {
    const session = await Session.findByPk(sessionId)
    return session !== null
}

export async function renewSession(sessionId) {
    const session = await Session.findByPk(sessionId)
    if (session === null) {
        debug(`failed attempt to renew session ${sessionId}`)
        throw new Error('cannot find session')
    }

    if (!canSessionBeRenewed(session)) {
        throw new Error('session is not in idle state')
    }

    const now = dayjs()
    const expirationDate = now.add(1, 'hour')
    const expirationDateIdle = now.add(2, 'hour')
    const newSessionId = generate32CharSessionId()

    const newSession = await Session.update({
        id: newSessionId,
        expirationDate,
        expirationDateIdle
    }, {
        where: {
            id: sessionId
        }
    })

    return await Session.findByPk(newSessionId)
}