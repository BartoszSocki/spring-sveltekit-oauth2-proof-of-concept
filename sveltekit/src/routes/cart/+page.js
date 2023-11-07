import { browser } from '$app/environment'
import { getCart } from '$lib/shared/cart/cart.js'
import { PUBLIC_BACKEND_URL } from '$env/static/public'

export async function load({ fetch }) {
    if (!browser) {
        return;
    }

    const prodcutIds = getCart().join(',')

    const urlSuffix = prodcutIds.length === 0 ? '' : `?cart=${prodcutIds}`;

    const response = await fetch(`${PUBLIC_BACKEND_URL}/api/cart${urlSuffix}`)

    const data = await response.json()

    return { data };
}