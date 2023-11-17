import { API_URL } from '$env/static/private'
import query from './query.gql?raw'

export async function load({ params, url, fetch }) {
    let searchSort = paramsToSortConverter(url.searchParams)
    let offset = params.page

    const variables = {
        pageable: pageable(offset * 2, 2),
        sort: searchSort
    };

    const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            query, variables
        })
    });

    const json = await response.json();

    return json.data.searchBoughtProducts;
}

function paramsToSortConverter(params) {
    const fieldName = params.get('orderBy') ?? ""
    const sortDir = params.get('sortDir') ?? ""
    const defaultSort = sort('date', 'DSC')

    if (!['date'].includes(fieldName)) {
        return defaultSort;
    }

    if (!['ASC', 'DSC'].includes(sortDir)) {
        return defaultSort;
    }

    return sort(fieldName, sortDir)
}

function sort(fieldName, sortDirection) {
    return {
        fieldName, sortDirection
    }
}

function pageable(offset, limit) {
    return {
        offset, limit
    }
}