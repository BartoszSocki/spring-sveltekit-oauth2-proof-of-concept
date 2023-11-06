import { json } from '@sveltejs/kit'

const query = `
query FindProductsByIds($ids: [Int!]!) {
    findProductsByIds(ids: $ids) {
        id
        name
        price {
            amount
            currency
        }
    }
} 
`

export async function GET({ params, url }) {
    const cartItemsIds = url.searchParams.get('cart')
    if  (cartItemsIds === undefined || cartItemsIds === null || cartItemsIds === "") {
        return json({
            products: []
        })
    }

    const ids = cartItemsIds.split(',').map(id => parseInt(id)) 

    console.log(ids)

    const variables = {
        ids
    };

    const response = await fetch('http://localhost:9090/graphql', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            query, variables
        })
    });

    const data = await response.json();

    return json(data.data.findProductsByIds)
}


// export async function GET(event) {
//     // console.log({event})
    
//     return await json({
//         products: [
//             {
//                 id: 1,
//                 name: 'Name',
//                 price: {
//                     amount: 10,
//                     currency: 'USD'
//                 }
//             }
//         ]
//     })
// }