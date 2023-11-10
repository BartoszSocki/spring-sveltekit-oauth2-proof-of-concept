import query from './query.gql?raw'
import { API_URL } from '$env/static/private'

export const actions = {
    default: async ({ request, fetch }) => {
        const data = await request.formData();
        
        // validation
        // ...
        const rawTags = data.get('tags')
        const tags = rawTags ? rawTags.split(',') : []

        console.log(tags)

        const variables = {
            product: {
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

        // check if all was good 
        // redirect afterwards
    
    }
}