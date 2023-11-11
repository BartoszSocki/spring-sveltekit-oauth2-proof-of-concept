import query from './query.gql?raw'
import { API_URL } from '$env/static/private'

export const actions = {
    default: async ({ request, fetch }) => {
        const data = await request.formData();

        console.log(data)
        const productsIds = data.get('products_ids').split(',').map(x => parseInt(x))
        const productsQuantity = data.get('products_quantity').split(',').map(x => parseInt(x))

        if (productsIds.length !== productsQuantity.length) {
            console.log("TODO: lengths are not the same")
        }

        const products = productsIds.map((id, i) => {
            return {
                productId: id,
                priductQuantity: productsQuantity.at(i)
            }
        })

        const variables = {
            address: {
                country: data.get('country'),
                city: data.get('city'),
                postalCode: data.get('postal_code'),
                addressLine: data.get('street'),
            },
            products
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

        const result = await response.json()

        console.log(result)
    }
}