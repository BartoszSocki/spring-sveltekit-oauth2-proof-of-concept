<script>
	import Product from "$lib/Product/Product.svelte";

    export let data;
    // $: console.log(data)
    $: products = data.data.searchProducts.content
    // $: products = []
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
        list-style: none;
        list-style-position: inside;
    }

    ul {
        padding-left: 0;
        display: flex;
        flex-direction: column;
        gap: 1rem;
    }
</style>