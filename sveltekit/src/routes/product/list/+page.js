export function _SearchProductsVariables({ url }) {
    let filters = [];
    for (const [key, value] of url.searchParams.entries()) {
        const filter = paramToFilterConverter(key, value)
        if (filter !== undefined && filter !== null) {
            filters.push(filter)
        }
    }

    let offset = parseInt(url.searchParams.get('offset') ?? '0') ?? 0;
    let limit = parseInt(url.searchParams.get('limit') ?? '10') ?? 10;
    let sortField = url.searchParams.get('sort') ?? 'name';
    let sortDir = url.searchParams.get('sortDir') ?? "ASC";

    return {
        filters,
        page: page(offset, limit),
        sort: sort(sortField, sortDir)
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