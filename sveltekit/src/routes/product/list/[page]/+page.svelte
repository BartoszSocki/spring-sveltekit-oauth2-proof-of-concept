<script>
	import Product from "$lib/Product/Product.svelte";
    import { page } from '$app/stores';
    import { invalidate } from '$app/navigation'

    export let data;

    const pageNumber = parseInt($page.params.page);
    let nextUrl, prevUrl;

    $: {
        let url = $page.url
        nextUrl = [url.origin, '/product/list/2', url.search].join('');
        prevUrl = [url.origin, '/product/list/0', url.search].join('');
    } 

    const reload = async () => {
        invalidate('/product/list')
    }

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
    <footer>
        {#if pageNumber > 0}
        <a href={prevUrl} on:click={reload}>prev</a>
        {/if}
        <a href={nextUrl} on:click={reload}>next</a>
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