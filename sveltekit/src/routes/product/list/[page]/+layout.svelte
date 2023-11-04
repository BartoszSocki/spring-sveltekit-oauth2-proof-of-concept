<script>
    import { page } from '$app/stores'
    import PriceFilter from '$lib/Filter/PriceFilter.svelte';
	import { writable } from 'svelte/store';
    import { onMount } from 'svelte';

    export let data;
    const filterParams = writable({})

    const reload = async () => {
        invalidateAll()
    }

    $: isLastPage = data.data.searchProducts.isLastPage
    $: isFirstPage = data.data.searchProducts.isFirstPage
    $: prevUrl = $page.url.origin + '/product/list/' + (parseInt($page.params.page) - 1) + $page.url.search;
    $: nextUrl = $page.url.origin + '/product/list/' + (parseInt($page.params.page) + 1) + $page.url.search;


    function toUrl() {
        const map = $filterParams
        const search = Object.entries(map)
            .filter(([_, value]) => value !== null && value !== undefined && value !== "")
            .map(([key, value]) => key + '=' + value)
            .join('&')
        
        console.log(search)
    }

    onMount(() => {
        const urlParams = new URLSearchParams(window.location.search);
        filterParams.set({
            priceFrom: urlParams.get('priceFrom'),
            priceTo: urlParams.get('priceTo') 
        })
    })

</script>

<main>
    <div class="filters">
        <PriceFilter filterParams={$filterParams} />
        <button on:click={() => toUrl(filterParams)}>search</button>
    </div>

    <slot />

    <footer>
        {#if !isFirstPage}
            <a href={prevUrl} on:click={reload}>prev</a>
        {/if}
        {#if !isLastPage}
            <a href={nextUrl} on:click={reload}>next</a>
        {/if}
    </footer>
</main>

<style>
    div.filters {
        display: flex;
        flex-direction: column;
        border: 1px solid white;
        padding: 1rem;
    }

    footer {
        display: flex;
        justify-content: center;
        gap: 1ch;
    }

</style>