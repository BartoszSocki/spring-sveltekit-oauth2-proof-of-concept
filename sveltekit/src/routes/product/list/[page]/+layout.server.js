const query = `
query SearchProducts($filters: [SearchFilterInput]!, $pageable: PageableInput!, $sort: SortInput!) {
    searchProducts(filters: $filters, pageable: $pageable, sort: $sort) {
        content {
            id
            name
            category
            tags
            description
            ownerId
            price {
                amount
                currency
            }
            quantity
            productScore {
                averageScore
                reviewsCount
            }
            imageUrl
        }
        isLastPage
        isFirstPage
    }
} 
`

export async function load({ params, url }) {
    let filters = [];
    for (const [key, value] of url.searchParams.entries()) {
        const filter = paramToFilterConverter(key, value)
        if (filter !== undefined && filter !== null) {
            filters.push(filter)
        }
    }

    let sortField = url.searchParams.get('sort') ?? 'name';
    let sortDir = url.searchParams.get('sortDir') ?? "ASC";
    let offset = params.page

    const variables = {
        filters,
        pageable: pageable(offset * 2, 2),
        sort: sort(sortField, sortDir)
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

    const json = await response.json();

    return json;
}

function paramToFilterConverter(param, value) {
    if (param === 'category') {
        // check if value is safe
        return filter('category', 'EQ', value)
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

function pageable(offset, limit) {
    return {
        offset, limit
    }
}

function sort(fieldName, sortDirection) {
    return {
        fieldName, sortDirection
    }
}