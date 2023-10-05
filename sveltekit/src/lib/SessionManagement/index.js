import { Session } from '$lib/db';
import { getRandomValues } from 'crypto'
import dayjs from 'dayjs';
import { anonymousUserId } from '$lib/db';

const base64Alphabet = 'abcdefghijklkmnopqrstuvwxyz12345ABCDEFGHIJKLKMNOPQRSTUVWXYZ67890'

function generateRandomBase64StringOfLength(length) {
    const entropy = new Uint8Array(length)
    getRandomValues(entropy)

    return Array.from(entropy).map(i => i >> 2).map(i => base64Alphabet.charAt(i)).join('')
}

function generate32CharSessionId() {
    return generateRandomBase64StringOfLength(32)
}

function configureExpiration() {
    const now = dayjs()
    const expirationDate = now.add(1, 'hour').toDate()
    const expirationDateIdle = now.add(2, 'hour').toDate()

    return {
        expirationDate,
        expirationDateIdle
    }
}

export async function newAnonymousSession({ ipAddress }) {
    const id = generate32CharSessionId()

    const session = await Session.create({
        id,
        ipAddress,
        userId: anonymousUserId,
        ...configureExpiration()
    })

    return session
}

export async function rotateSession({ oldSessionId, userId, ipAddress }) {
    const newSessionId = generate32CharSessionId()

    await Session.destroy({ where: { id: oldSessionId } })

    const session = await Session.create({
        id: newSessionId,
        userId,
        ipAddress,
        ...configureExpiration()
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
    return session !== null && now.isAfter(session.expirationDate) && now.isBefore(session.expirationDateIdle)
}

function isSessionDead(session) {
    const now = dayjs()
    return session !== null && now.isAfter(session.expirationDateIdle)
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

    const sessionStatus = await getSessionStatus(sessionId)
    if (sessionStatus !== SessionStatus.Idle) {
        throw new Error('session is not in idle state')
    }

    const newSessionId = generate32CharSessionId()
    await Session.update({
        id: newSessionId,
        ...configureExpiration()
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
    const state = generateRandomBase64StringOfLength(64)
    await saveStateForSession(sessionId, state)
    return state
}

export async function isStateParameterValidForSession(sessionId, state) {
    const session = await Session.findByPk(sessionId)
    return session !== undefined && session !== null && session.state === state
}