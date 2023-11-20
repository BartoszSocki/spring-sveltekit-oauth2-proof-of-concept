import { API_URL } from '$env/static/private'
import query from './query.gql?raw'
import { findUserBySessionId } from '$lib/server/SessionManagement/index.js';
import { logger } from '$lib/server/Logger'

export async function load({ cookies, params, url, fetch }) {
    const sessionId = cookies.get('sessionid')
    const user = await findUserBySessionId(sessionId)
    logger.info('user products: fetched user correlated with session: ' + user.email)

    const userFilter = filter('user', 'EQ', user.email)
    logger.info('user filter for products added: ', JSON.stringify(userFilter))

    let filters = [userFilter];

    for (const [key, value] of url.searchParams.entries()) {
        const filter = paramToFilterConverter(key, value)
        if (filter !== undefined && filter !== null) {
            filters.push(filter)
        }
    }

    let searchSort = paramsToSortConverter(url.searchParams)
    let offset = params.page

    const variables = {
        filters,
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

    return json;
}

function paramToFilterConverter(param, value) {
    if (param === 'category') {
        // check if value is safe
        return filter('category', 'EQ', value)
    }

    if (param === 'priceTo') {
        return filter('price', 'LT', value)
    }

    if (param === 'priceFrom') {
        return filter('price', 'GT', value)
    }

    if (param === 'name') {
        return filter('name', 'LIKE', value)
    }

    if (param === 'tags') {
        if (value === undefined || value === null || value === "") {
            return null
        }
        const tags = value.split(',').filter(t => t !== "")
        return filter('tag', 'IN', tags.join(','))
    }
}

function filter(fieldName, searchOperation, fieldValue) {
    return {
        fieldName, searchOperation, fieldValue
    }
}

function pageable(offset, limit) {
    return {
        offset, limit
    }
}

function paramsToSortConverter(params) {
    const fieldName = params.get('orderBy') ?? ""
    const sortDir = params.get('sortDir') ?? ""
    const defaultSort = sort('name', 'ASC')

    if (!['score', 'price', 'name'].includes(fieldName)) {
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