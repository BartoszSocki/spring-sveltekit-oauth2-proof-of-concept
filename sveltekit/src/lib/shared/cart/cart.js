export function getCart() {
    if (!window.localStorage.getItem('cart-products')) {
        return [];
    }

    return window.localStorage.getItem('cart-products').split(',')
}

function save(state) {
    window.localStorage.setItem('cart-products', state.join(','))
}

export function removeFromCart(id) {
    const newState = getCart().filter(p => p !== id)
    save(newState)
}

export function addToCart(id) {
    const newState = [...getCart(), id].join(',')
    save(newState)
}