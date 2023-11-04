<script>
    import "@picocss/pico/css/pico.css"

    export let data;
    $: isUserAuthenticated = data.isUserAuthenticated;
</script>

<main>
    <nav>
        <ul>
            <li>
                <a href="/">
                    <h1>Allegrissimo</h1>
                </a>
            </li>
            <li>
                <a href="/product/list/0">search</a>
            </li>
            {#if isUserAuthenticated}
            <li>
                <a href="/product/add">add new product</a>
            </li>
            <li>
                <a href="/bought-product">bought products</a>
            </li>
            {/if}
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
    * {
        box-sizing: border-box
    }

    main {
        padding: 0.5rem;
        height: 100vh;
        width: clamp(40rem, 100%, 90rem);
        margin-inline: auto;
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
