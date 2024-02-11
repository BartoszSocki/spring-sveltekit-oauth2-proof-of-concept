import { API_URL } from '$env/static/private'
import { logger } from '$lib/server/Logger'

export async function load({ params, url }) {
    logger.debug("loading product list...");

    let offset = params.page;

    const apiUrl = pageInfo(
        new URL(`${API_URL}/api/products`),
        {
            page: offset,
            size: 10
        }
    );

    const response = await fetch(apiUrl);
    const products = await response.json();
    logger.debug("loaded product list: " + JSON.stringify(products));

    return { products };
}

function pageInfo(url, { page, size }) {
    url.searchParams.append("page", page);
    url.searchParams.append("size", size);

    return url;
}