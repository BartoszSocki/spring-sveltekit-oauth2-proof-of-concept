import { writable } from 'svelte/store';
import { browser } from '$app/environment';

const defaultValue = '';
const initialValue = browser ? window.localStorage.getItem('cart-products') ?? defaultValue : defaultValue
const CartStore = writable(initialValue);

CartStore.subscribe((value) => {
    if (browser) {
        window.localStorage.setItem('cart-products', value);
    }
});

export default CartStore;