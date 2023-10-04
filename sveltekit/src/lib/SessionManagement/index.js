import { Session } from '$lib/db';
import { getRandomValues } from 'crypto'
import dayjs from 'dayjs';
import { anonymousUserId } from '$lib/db';
import { logger } from '$lib/Logger'

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
    const expirationDate = now.add(1, 'hour').toDate()
    const expirationDateIdle = now.add(2, 'hour').toDate()

    const session = await Session.create({
        id: sessionId,
        ipAddress,
        userId: anonymousUserId,
        expirationDate,
        expirationDateIdle
    })

    return session
}

export async function rotateSession({ oldSessionId, userId, ipAddress }) {
    const newSessionId = generate32CharSessionId()
    const now = dayjs()
    const expirationDate = now.add(1, 'hour').toDate()
    const expirationDateIdle = now.add(2, 'hour').toDate()

    await Session.destroy({ where: { id: oldSessionId } })

    const session = await Session.create({
        id: newSessionId,
        userId: userId,
        ipAddress: ipAddress ?? null,
        expirationDate,
        expirationDateIdle
    })

    return session
}

export async function doesSessionExists(sessionId) {
    const session = await Session.findByPk(sessionId)
    return session !== null
}


export const SessionStatus = {
    Active: 'Active',
    Idle: 'Idle',
    Dead: 'Dead',
    NotExists: 'NotExists'
}

function isSessionActive(session) {
    const now = dayjs()
    return session !== null && now.isBefore(session.expirationDate)
}

function isSessionIdle(session) {
    const now = dayjs()
    return session !== null && now.isSameOrAfter(session.expirationDate) && now.isBefore(session.expirationDateIdle)
}

function isSessionDead(session) {
    const now = dayjs()
    return session !== null && now.isSameOrAfter(session.expirationDateIdle)
}

export async function isSessionAnonymous(sessionId) {
    const session = await Session.findByPk(sessionId)
    return session.userId === anonymousUserId
}

export async function getSessionStatus(sessionId) {
    const session = await Session.findByPk(sessionId)
    if (isSessionActive(session)) {
        return SessionStatus.Active
    }

    if (isSessionIdle(session)) {
        return SessionStatus.Idle
    }

    if (isSessionDead(session)) {
        return SessionStatus.Dead
    }

    return SessionStatus.NotExists
}

export async function renewSession(sessionId) {
    const session = await Session.findByPk(sessionId)
    if (session === null) {
        throw new Error('cannot find session')
    }

    if (!canSessionBeRenewed(session)) {
        throw new Error('session is not in idle state')
    }

    const now = dayjs()
    const expirationDate = now.add(1, 'hour').toDate()
    const expirationDateIdle = now.add(2, 'hour').toDate()
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

async function saveStateForSession(sessionId, state) {
    await Session.update({ state }, { where: { id: sessionId } })
}

export async function generateAndSaveStateForSession(sessionId) {
    const state = generate32CharSessionId() + generate32CharSessionId()

    logger.debug(`session state setting for: ${sessionId}, state: ${state}`)
    await saveStateForSession(sessionId, state)
    return state
}

export async function isStateParameterValidForSession(sessionId, state) {
    logger.debug(`session state retrieval for: ${sessionId}, state: ${state}`)

    const session = await Session.findByPk(sessionId)
    return session !== undefined && session !== null && session.state === state
}