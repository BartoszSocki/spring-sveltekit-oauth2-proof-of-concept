import { REST_API_URL } from '$env/static/private'
import { redirect } from '@sveltejs/kit';
import { logger } from '$lib/server/Logger/index.js';

export const actions = {
    default: async ({ request, fetch, params }) => {
        const data = await request.formData();
        
        // validation
        const fiveStarScore = data.get('five-star-score')
        const review = data.get('review')
        const productId = params.slug

        const response = await fetch(`${REST_API_URL}/product/${productId}/review`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                fiveStarScore,
                review
            })
        });

        if (response.status >= 400) {
            logger.info(`redirecting with error, user already added review for product with id: ${productId}`)
            throw redirect(303, "/bought-product/list/0?status=error-already-added-review")
        }

        logger.info(`redirecting with success, user added review for product with id: ${productId}`)
        throw redirect(303, "/bought-product/list/0?status=success")
    }
}