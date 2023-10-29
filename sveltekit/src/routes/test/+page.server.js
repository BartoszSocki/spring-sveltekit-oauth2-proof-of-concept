import { logger } from '$lib/server/Logger'

export async function load({ params, fetch }) {
    try {
        const response = await fetch('http://localhost:9090/api/principal')
        return await response.json()
    } catch (e) {
        logger.debug(e.toString())
        return {}
    }
}