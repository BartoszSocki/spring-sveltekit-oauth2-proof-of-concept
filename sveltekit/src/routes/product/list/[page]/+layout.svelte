<script>
    import { page } from '$app/stores'
    import PriceFilter from '$lib/Filter/PriceFilter.svelte';
    import CategoryFilter from '$lib/Filter/CategoryFilter.svelte';
	import { writable } from 'svelte/store';
    import { onMount } from 'svelte';
    import { goto } from '$app/navigation'

    export let data;
    const filterParams = writable({})
    const isValid = writable({})

    // const reload = async () => {
    //     // invalidateAll()
    // }

    $: isLastPage = data.data.searchProducts.isLastPage
    $: isFirstPage = data.data.searchProducts.isFirstPage
    $: prevUrl = $page.url.origin + '/product/list/' + (parseInt($page.params.page) - 1) + $page.url.search;
    $: nextUrl = $page.url.origin + '/product/list/' + (parseInt($page.params.page) + 1) + $page.url.search;


    function search() {
        const map = $filterParams
        const search = Object.entries(map)
            .filter(([_, value]) => value !== null && value !== undefined && value !== "")
            .map(([key, value]) => key + '=' + value)
            .join('&')
        
        const url = '/product/list/0?' + search;
        goto(url)
        
    }

    onMount(() => {
        const urlParams = new URLSearchParams(window.location.search);
        filterParams.set({
            priceFrom: urlParams.get('priceFrom'),
            priceTo: urlParams.get('priceTo'),
            category: urlParams.get('category') 
        })
    })

    $: filtersState = $isValid
    $: isDataValid = Object.entries(filtersState).every(([_, state]) => state)

</script>

<main>
    <div class="filters">
        <PriceFilter filterParams={$filterParams} isValid={isValid} />
        <CategoryFilter filterParams={$filterParams} isValid={isValid} />
        
        <button on:click={search} disabled={!isDataValid}>search</button>
    </div>

    <slot />

    <footer>
        {#if !isFirstPage}
            <a href={prevUrl}>prev</a>
        {/if}
        {#if !isLastPage}
            <a href={nextUrl}>next</a>
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
        padding-block: 1rem;
    }
</style>