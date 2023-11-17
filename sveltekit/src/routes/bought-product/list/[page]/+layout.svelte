<script>
    import { page } from '$app/stores'
    import { goto } from "$app/navigation";

    export let data;

    $: isLastPage = data.isLastPage
    $: isFirstPage = data.isFirstPage
    $: prevUrl = $page.url.origin + '/bought-product/list/' + (parseInt($page.params.page) - 1) + $page.url.search;
    $: nextUrl = $page.url.origin + '/bought-product/list/' + (parseInt($page.params.page) + 1) + $page.url.search;

    $: status = $page.url.searchParams.get('status')
    $: success = status === 'success'
    $: error = status === 'error'

    function removeStatusHeader() {
        $page.url.searchParams.delete('status')
        goto($page.url.href)
    }
</script>

<main>
    <header>
        {#if error}
            <div class="status status--error">
                <div class="status__title">unexpected error occured</div>
                <div class="status__grow"/>
                <button on:click={removeStatusHeader}>X</button>
            </div>
        {/if}
        {#if success}
            <div class="status status--success">
                <span class="status__title">success</span>
                <button on:click={removeStatusHeader}>X</button>
            </div>
        {/if}
    </header>
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

    .status {
        padding: 1rem;
        display: flex;
        flex-direction: row;
        gap: 1rem;
    }

    .status__title {
        flex-grow: 1;
    }

    .status--error {
        background-color: #621212;
        border: 2px solid red;
    }

    .status--success {
        background-color: #12400c;
        border: 2px solid #4aec28;
    }

    button {
        width: fit-content;
        padding: 0; 
        margin: 0;
        background: none;
        border: none;
        font-size: inherit;
        color: white;
    }

    button:hover {
        color: #a8d5ed;
    }
</style>