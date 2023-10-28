<script>
	import Product from "$lib/Product/Product.svelte";

    export let data;

    $: ({SearchProducts} = data);
    $: console.log($SearchProducts.data)
</script>

<section>
    hello this is the product list
    <!-- {$SearchProducts.data.searchProducts} -->
    <ul>
        {#each $SearchProducts.data.searchProducts as product}
            <li>
                <Product product={product} />
            </li>
        {/each}
    </ul>
</section>

<style>
    ul {
        list-style: none;
    }

    ul {
        display: flex;
        flex-direction: column;
        gap: 1rem;
    }
</style>