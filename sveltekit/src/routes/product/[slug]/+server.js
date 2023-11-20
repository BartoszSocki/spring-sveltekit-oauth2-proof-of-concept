import { logger } from '$lib/server/Logger/index.js'
import { REST_API_URL } from '$env/static/private'
import { json, redirect } from '@sveltejs/kit'

export async function DELETE({ params, fetch }) {
    const productId = params.slug

    const response = await fetch(`${REST_API_URL}/product/${productId}`, {
        method: 'DELETE'
    })

    if (response.status >= 400) {
        logger.info('cannot delete product with id: ' + productId)
        return json({
            status: "error"
        })
    }

    logger.info('deleted product with id: ' + productId)
    return json({
        status: "success"
    })
}