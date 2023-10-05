import { logger } from '$lib/Logger'

export async function load({ params, fetch }) {
    try {
        const response = await fetch('http://localhost:9090/api/test')
        return await response.json()
    } catch (e) {
        logger.debug(e.toString())
        return {}
    }
}