<script>
	import Product from "$lib/Product/Product.svelte";

    export let data;

    $: ({SearchProducts} = data);
</script>

<section>
    <ul>
        {#each $SearchProducts.data.searchProducts as product}
            <li>
                <Product product={product} />
            </li>
        {/each}
    </ul>
</section>

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