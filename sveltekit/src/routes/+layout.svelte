<script>
    import "@picocss/pico/css/pico.css"

    export let data;
    $: isUserAuthenticated = data.isUserAuthenticated;
</script>

<main>
    <nav>
        <ul>
            <li>
                <h1>Allegrisimo</h1>
            </li>
            <li>
                <a href="/product/list">search</a>
            </li>
            <li>
                <a href="/product/add">add new product</a>
            </li>
            <li>
                <a href="/bought-product">bought products</a>
            </li>
            <div class="space" />
            <li>
                <a href="/profile">profile</a>
            </li>
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
        width: 100%;
        display: flex;
        flex-direction: row;
    }
    
    h1 {
        margin: 0;
    }

    div.space {
        flex-grow: 1
    }

</style>
