import { logger } from '$lib/server/Logger/index.js'

export async function DELETE({ params }) {
    const productId = params.slug

    logger.info('user deleted product with id: ' + productId)
}