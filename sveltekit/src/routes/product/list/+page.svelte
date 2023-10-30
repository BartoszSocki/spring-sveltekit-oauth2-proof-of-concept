<script>
	import Product from "$lib/Product/Product.svelte";
    import { page } from '$app/stores';

    export let data;

    $: currentOffset = parseInt($page.url.searchParams.get('offset'))
    $: currentLimit = parseInt($page.url.searchParams.get('limit'))

    let nextUrl, prevUrl;

    $: {
        let url = $page.url
        url.searchParams.set('offset', currentOffset + currentLimit);
        nextUrl = url.href;
        url.searchParams.set('offset', Math.max(0, currentOffset - currentLimit));
        prevUrl = url.href;
    } 

    $: ({SearchProducts} = data);
    // $: console.log($SearchProducts.data)
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
        {#if currentOffset > 0}
        <a href={prevUrl} target="_self">prev</a>
        {/if}
        <a href={nextUrl} target="_self">next</a>
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
        gap: 1rem;
        justify-content: center;
    }
</style>