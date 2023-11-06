<script>
	import CartProduct from '$lib/Cart/CartProduct.svelte';
    import { removeFromCart } from '$lib/shared/cart/cart.js'

    export let data;

    $: console.log(data)

    $: products = data.data;
    $: canBeBought = products !== undefined && products !== null && products.length > 0;

    function removeItemFromCart(id) {
        products = products.filter(p => p.id !== id)
        removeFromCart(id)
    }
</script>

<div>
    <ul>
        {#if products && products.length > 0}
            {#each products as product (product.id)}
                <li>
                    <CartProduct product={product}/>
                    <div class="space" />
                    <!-- <span>quantity: </span> -->
                    <!-- <button class="btn quantity">+</button> -->
                    <!-- <button class="btn quantity">-</button> -->
                    <button class="btn delete" on:click={() => removeItemFromCart(product.id)}>delete</button>
                </li>
            {/each}
        {:else}
            <span>cart is empty</span>
        {/if}
    </ul>

    <footer>
        <button disabled={!canBeBought}>buy</button>
    </footer>
</div>

<style>
    li {
        list-style: none;
        list-style-position: inside;
        padding: 1rem 1rem;

        background-color: rgb(37, 51, 65);
        border-radius: 8px;

        display: inline-flex;
        gap: 2.25rem;
        flex-direction: row;
        align-items: center;
    }

    li:nth-child(even) {
        background-color: transparent;
    }

    ul {
        padding-left: 0;
        display: flex;
        flex-direction: column;
        gap: 0.25rem;
    }

    .btn {
        display: inline-block;
        width: auto;
        margin: 0;
        padding: 0;
        background-color: transparent;
        border: none;
    }

    .btn.delete {
        background-color: transparent;
        border: none;
        color: red;
        font-weight: bold;
    }

    .btn.quantity {
        color: white;
        font-weight: bold;
        /* font-size: 2rem; */
    }

    .quantity:hover {
        color: #a8d5ed;
    }

    div.space {
        flex-grow: 1
    }
</style>