import { PROD } from '$env/static/private'

export function info(message) {
    console.info(`[*] ${message}`)
}

export function log(message) {
    console.log(`[*] ${message}`)
}

export function debug(message) {
    if (PROD !== undefined && PROD === 'true') {
        return
    }

    log(`[ debug ] ${message}`)
}