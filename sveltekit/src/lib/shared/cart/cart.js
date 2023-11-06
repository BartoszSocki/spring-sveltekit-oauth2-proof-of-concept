export function getCart() {
    if (!window.localStorage.getItem('cart-products')) {
        return [];
    }

    return window.localStorage.getItem('cart-products').split(',')
}

function save(state) {
    console.log(state)
    window.localStorage.setItem('cart-products', state.join(','))
}

export function removeFromCart(id) {
    const newState = getCart().filter(p => p !== id)
    save(newState)
}

export function addToCart(id) {
    const state = getCart()
    if (state.includes(id)) {
        return
    }

    const newState = [...state, id]
    save(newState)
}