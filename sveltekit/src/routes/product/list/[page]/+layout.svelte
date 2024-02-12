<script>
    import { page } from '$app/stores'
	import { writable } from 'svelte/store';
    import { onMount } from 'svelte';
    import { goto } from '$app/navigation'

    import PriceCriteria from '../../../../lib/product/query/PriceCriteria.svelte';
    import CategoryCriteria from '../../../../lib/product/query/CategoryCriteria.svelte'
	import SortCriteria from '../../../../lib/product/query/SortCriteria.svelte';
	import TagsCriteria from '../../../../lib/product/query/TagsCriteria.svelte';

    export let data;
    const criteria = writable({})
    const isValid = writable({})

    // $: isLastPage = data.data.searchProducts
    // $: isFirstPage = data.data.searchProducts
    $: prevUrl = $page.url.origin + '/product/list/' + (parseInt($page.params.page) - 1) + $page.url.search;
    $: nextUrl = $page.url.origin + '/product/list/' + (parseInt($page.params.page) + 1) + $page.url.search;

    function search() {
        const map = $criteria
        const search = Object.entries(map)
            .filter(([_, value]) => value !== null && value !== undefined && value !== "")
            .map(([key, value]) => key + '=' + value)
            .join('&')
        
        const url = '/product/list/0?' + search;
        goto(url) 
    }

    onMount(() => {
        const urlParams = new URLSearchParams(window.location.search)
        const tagsInUrl = urlParams.get('tags')
        const tags = tagsInUrl === null || tagsInUrl === "" ? [] : tagsInUrl.split(',')

        criteria.set({
            priceFrom: urlParams.get('priceFrom'),
            priceTo: urlParams.get('priceTo'),
            category: urlParams.get('category'),
            orderBy: urlParams.get('orderBy'),
            sortDir: urlParams.get('sortDir'),
            tags
        });
    })

    $: filtersState = $isValid
    $: isDataValid = Object.entries(filtersState).every(([_, state]) => state)

</script>

<main>
    <div class="criteria">
        <PriceCriteria criteria={criteria} isValid={isValid} />
        <CategoryCriteria searchParams={criteria} isValid={isValid} />
        <TagsCriteria searchParams={criteria} />
        <SortCriteria searchParams={criteria} />
        
        <button on:click={search} disabled={!isDataValid}>search</button>
    </div>

    <slot />

    <footer>
        <!-- {#if !isFirstPage}
            <a href={prevUrl}>prev</a>
        {/if}
        {#if !isLastPage}
            <a href={nextUrl}>next</a>
        {/if} -->
    </footer>
</main>

<style>
    div.criteria {
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