<script>
	import Product from "$lib/Product/Product.svelte";
    import { invalidateAll } from '$app/navigation'
    import { page } from '$app/stores'

    export let data;

    const reload = async () => {
        invalidateAll()
    }

    $: products = data.data.searchProducts.content
    $: isLastPage = data.data.searchProducts.isLastPage
    $: isFirstPage = data.data.searchProducts.isFirstPage
    $: prevUrl = $page.url.origin + '/product/list/' + (parseInt($page.params.page) - 1) + $page.url.search;
    $: nextUrl = $page.url.origin + '/product/list/' + (parseInt($page.params.page) + 1) + $page.url.search;

</script>

<section>
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
    <footer>
        {#if !isFirstPage}
            <a href={prevUrl} on:click={reload}>prev</a>
        {/if}
        {#if !isLastPage}
            <a href={nextUrl} on:click={reload}>next</a>
        {/if}
    </footer>
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
    
    footer {
        display: flex;
        justify-content: center;
        gap: 1ch;
    }
</style>