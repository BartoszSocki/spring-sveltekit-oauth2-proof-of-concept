<script>
    import { page } from '$app/stores'

    export let data;

    $: isLastPage = data.isLastPage
    $: isFirstPage = data.isFirstPage
    $: prevUrl = $page.url.origin + '/bought-product/list/' + (parseInt($page.params.page) - 1) + $page.url.search;
    $: nextUrl = $page.url.origin + '/bought-product/list/' + (parseInt($page.params.page) + 1) + $page.url.search;

</script>

<main>
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
    footer {
        display: flex;
        justify-content: center;
        gap: 1ch;
        padding-block: 1rem;
    }
</style>