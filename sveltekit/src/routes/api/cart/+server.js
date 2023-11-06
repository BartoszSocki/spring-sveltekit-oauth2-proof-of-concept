import { json } from '@sveltejs/kit'

export async function GET(event) {
    // console.log({event})
    
    return await json({
        products: [
            {
                id: 1,
                name: 'Name',
                price: {
                    amount: 10,
                    currency: 'USD'
                }
            }
        ]
    })
}