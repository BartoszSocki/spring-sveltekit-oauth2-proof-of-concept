<script>
	import Product from "$lib/Product/Product.svelte";
    import { invalidateAll } from '$app/navigation'

    export let data;

    // const pageNumber = parseInt($page.params.page);
    // let nextUrl, prevUrl;

    // $: {
    //     let url = $page.url
    //     nextUrl = [url.origin, '/product/list/2', url.search].join('');
    //     prevUrl = [url.origin, '/product/list/0', url.search].join('');
    // } 

    const reload = async () => {
        invalidateAll()
    }

    $: products = data.data.searchProducts

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
    <a href="/product/list/1" on:click={reload}>next</a>
    <!-- <footer>
        {#if pageNumber > 0}
        <a href={prevUrl} on:click={reload}>prev</a>
        {/if}
        <a href={nextUrl} on:click={reload}>next</a>
    </footer> -->
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