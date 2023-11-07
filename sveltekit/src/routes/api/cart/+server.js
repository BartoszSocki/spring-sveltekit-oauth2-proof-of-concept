import { error, json } from '@sveltejs/kit'
import { API_URL } from '$env/static/private'
import { positiveIntegerRegex } from '$lib/util/validation.js'
import query from './query.gql?raw'

export async function GET({ url }) {
    const cartItemsIds = url.searchParams.get('cart')

    if  (!cartItemsIds) {
        return json({
            products: []
        })
    }

    const ids = getIds(cartItemsIds)

    const variables = { ids };

    const response = await fetch(API_URL, {
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

function getIds(value) {
    const stringIds = value.split(',')

    const areIdsValid = stringIds.map(id => positiveIntegerRegex.test(id))
        .every(id => id)

    if (!areIdsValid) {
        throw error(400, {
            message: 'ids contains invalid id'
        })
    }

    const ids = stringIds.map(id => parseInt(id))

    return ids
}