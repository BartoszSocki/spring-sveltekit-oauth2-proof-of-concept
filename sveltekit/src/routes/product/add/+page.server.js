import { REST_API_URL } from '$env/static/private'
import { redirect } from '@sveltejs/kit';
import { logger } from '../../../lib/server/Logger/index.js';

export const actions = {
    default: async ({ request, fetch }) => {
        const data = await request.formData();
        
        // validation
        // ...
        const rawTags = data.get('tags')
        const tags = rawTags ? rawTags.split(',') : []

        const product = {
            name: data.get('name'),
            description: data.get('description'),
            price: {
                amount: parseInt(data.get('amount')),
                currency: data.get('currency')
            },
            quantity: parseInt(data.get('quantity')),
            category: data.get('category'),
            tags, 
            imageUrl: data.get('image-url')
        } 

        const response = await fetch(`${REST_API_URL}/product`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                ...product
            })
        });

        if (response.status >= 400) {
            logger.info('failed to add new product')
            throw redirect(303, '/product/add?status=error')
        }

        logger.info('successfully added new product')
        throw redirect(303, '/?status=success-successfully-added-product')
    }
}