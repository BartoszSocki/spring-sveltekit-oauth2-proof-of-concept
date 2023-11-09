import query from './query.gql?raw'
import { API_URL } from '$env/static/private'

export const actions = {
    default: async ({ request }) => {
        const data = await request.formData();
        
        // validation
        // ...

        const variables = {
            name: data.get('name'),
            description: data.get('description'),
            price: {
                amount: data.get('amount'),
                currency: data.get('currency')
            },
            quantity: data.get('quantity'),
            category: data.get('category'),
            tags: data.get('tags'),
            imageUrl: data.get('image-url')
        } 

        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                query, variables
            })
        });
        
    }
}