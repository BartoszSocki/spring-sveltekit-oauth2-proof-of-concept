import { REST_API_URL } from '$env/static/private'
import { redirect } from '@sveltejs/kit';
import { logger } from '../../lib/server/Logger/index.js'

export const actions = {
    default: async ({ request, fetch }) => {
        const data = await request.formData();

        const productsIds = data.get('products_ids').split(',').map(x => parseInt(x))
        const productsQuantity = data.get('products_quantity').split(',').map(x => parseInt(x))

        if (productsIds.length !== productsQuantity.length) {
            console.log("TODO: lengths are not the same")
        }

        const products = productsIds.map((id, i) => {
            return {
                productId: id,
                productQuantity: productsQuantity.at(i)
            }
        })

        const response = await fetch(`${REST_API_URL}/products/buy`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                address: {
                    country: data.get('country'),
                    city: data.get('city'),
                    postalCode: data.get('postal_code'),
                    addressLine: data.get('street'),
                },
                products
            })
        });

        if (response.status >= 400) {
            logger.info(`error when trying to buy cart with products: ${productsIds}`)
            throw redirect(303, '/cart?status=error')
        }

        logger.info(`cart bought`)
        throw redirect(303, '/cart?status=success')
    }
}