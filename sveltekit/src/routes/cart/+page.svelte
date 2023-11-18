<script>
	import CartProduct from '$lib/cart/CartProduct.svelte';
    import { removeFromCart } from '$lib/shared/cart/cart.js'
	import Legend from '../../lib/component/Legend.svelte';

    export let data;

    $: products = data.data;
    $: canBeBought = products !== undefined && products !== null && products.length > 0;
    $: productsIds = products ? products.map(p => p.id) : []
    // for now there is no possibility of changing quantity
    $: productsQuantity = new Array(productsIds.length).fill(1)
    // $: totalPrice = products ? products.reduce((acc, cur) => ) : 0;

    function removeItemFromCart(id) {
        products = products.filter(p => p.id !== id)
        removeFromCart(id)
    }
</script>

<div>
    <ul>
        {#await products}
            Loading...
        {:then products}

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

        {:catch error}
            {error.message}
        {/await}
    </ul>

    <!-- <span>total price: {} USD</span> -->

    <form method="POST">
        <fieldset>
            <Legend>Shipping Address</Legend>
            <label>
                Country
                <input required pattern="[\w\s]+" name="country" type="text" placeholder="Country..." />
            </label>
            <label>
                City
                <input required pattern="[\w\s]+" name="city" type="text" placeholder="City..." />
            </label>
            <label>
                Postal Code
                <input required name="postal_code" type="text" placeholder="Postal Code..." />
            </label>
            <label>
                Street
                <input required pattern="[\w\s\d]+" name="street" type="text" placeholder="Street..." />
            </label>
        </fieldset>
        <input hidden name="products_ids" type="text" value={productsIds}>
        <input hidden name="products_quantity" type="text" value={productsQuantity}>
        <button disabled={!canBeBought}>buy</button>
    </form>
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

    div.space {
        flex-grow: 1
    }
</style>