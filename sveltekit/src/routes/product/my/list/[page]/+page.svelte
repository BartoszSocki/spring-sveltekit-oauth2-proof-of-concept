<script>
	import Product from "$lib/product/Product.svelte";
    import { addToCart } from '$lib/shared/cart/cart.js'

    export let data;
    // $: console.log(data)
    $: products = data.data.searchProducts.content
    // $: products = []

    function addItemToCart(id) {
        addToCart(id)
    }
</script>

<ul>
    {#if products !== undefined && products !== null}
        {#each products as product (product.id)}
            <li>
                <Product product={product}>
                    <button on:click={() => addItemToCart(product.id)}>add to cart</button>
                </Product>
            </li>
        {/each}
    {:else}
        <div>wow so empty here</div>
    {/if}
</ul>


<style>
    li {
        list-style: none;
        list-style-position: inside;
    }

    ul {
        padding-left: 0;
        display: flex;
        flex-direction: column;
        gap: 1rem;
    }

    button {
        margin: 0;
        padding: 1rem;
        height: fit-content;
    }
</style>