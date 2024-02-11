<script>
	import Product from "$lib/product/Product.svelte";
    import { addToCart } from '$lib/shared/cart/cart.js'

    export let data;
    $: ({ products } = data);
</script>

<ul>
    {#if products !== undefined && products !== null}
        {#each products as product (product.id)}
            <li>
                <Product product={product} />
            </li>
        {/each}
    {:else}
        <div>wow so empty here</div>
    {/if}
</ul>


<style>
    li {
        list-style-position: inside;
        border: 2px solid var(--border-primary);
        border-bottom: none;

        /* height: var(--product-height); */
        
        padding: 1rem;
    }

    li:last-child {
        border-bottom: 2px solid var(--border-primary);
    }

    ul {
        padding-left: 0;
        display: flex;
        flex-direction: column;
    }
</style>