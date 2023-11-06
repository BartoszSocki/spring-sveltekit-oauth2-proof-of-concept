import { browser } from '$app/environment'
import { getCart } from '$lib/shared/cart/cart.js'

export async function load({ fetch }) {
    if (!browser) {
        return;
    }

    const prodcutIds = getCart().join(',')

    const urlSuffix = prodcutIds.length === 0 ? '' : `?cart=${prodcutIds}`;

    const response = await fetch(`http://localhost:3000/api/cart${urlSuffix}`)
    const data = await response.json()

    return data;
}