<script>
    import "@picocss/pico/css/pico.css"

    export let data;
    $: isUserAuthenticated = data.isUserAuthenticated;
</script>

<main>
    <nav>
        <ul>
            <li>
                <a href="/product/list">search</a>
            </li>
            <li>
                <a href="/product/add">add new product</a>
            </li>
            <li>
                <a href="/bought-product">bought products</a>
            </li>
            <li>
                <a href="/profile">profile</a>
            </li>
            <!-- TODO: add auth guard -->
            <li>
            {#if !isUserAuthenticated}
                <a href="/login">sign in</a>
            {:else}
                <a href="/logout">sign out</a>
            {/if}
            </li>
        </ul>
    </nav>
    <slot />
</main>

<style>
    main {
        padding: 0.5rem;
    }

    ul {
        width: auto;
        display: flex;
        flex-direction: row;
    }
    
</style>
