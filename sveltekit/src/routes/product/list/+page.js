export function _SearchProductsVariables({ url }) {
    let filters = [];
    for (const [key, value] of url.searchParams.entries()) {
        filters.push(paramToFilterConverter(key, value))
    } 

    return {
        filters,
        page: page(0, 10),
        sort: sort('name', 'ASC')
    }
}

function paramToFilterConverter(param, value) {
    if (param === 'category') {
        // check if value is safe
        return filter('category', 'LIKE', value)
    }

    if (param === 'name') {
        return filter('name', 'LIKE', value)
    }
}

function filter(fieldName, searchOperation, fieldValue) {
    return {
        fieldName, searchOperation, fieldValue
    }
}

function page(offset, limit) {
    return {
        offset, limit
    }
}

function sort(fieldName, sortDirection) {
    return {
        fieldName, sortDirection
    }
}