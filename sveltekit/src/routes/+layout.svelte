<script>
    // import "@picocss/pico/css/pico.css"
    import "../style.css"
    import NavLink from "../lib/component/NavLink.svelte";

    export let data;
    $: ({ isUserAuthenticated } = data);
</script>

<main>
    <nav>
        <ul>
            <li>
                <NavLink href="/">
                    <h1>Allegrissimo</h1>
                </NavLink>
            </li>
            <li>
                <NavLink href="/product/list/0">search</NavLink>
            </li>
            {#if isUserAuthenticated}
            <li>
                <NavLink href="/product/add">add new product</NavLink>
            </li>
            <li>
                <NavLink href="/product/my/list/0">my products</NavLink>
            </li>
            <li>
                <NavLink href="/bought-product/list/0">bought products</NavLink>
            </li>
            {/if}
            <div class="space" />
            <li>
                <NavLink href="/cart">cart</NavLink>
            </li>
            <li>
                <NavLink href="/profile">profile</NavLink>
            </li>
            <li>
            {#if !isUserAuthenticated}
                <NavLink href="/login">sign in</NavLink>
            {:else}
                <NavLink href="/logout">sign out</NavLink>
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
        padding-inline: 1rem;
        height: 100vh;
        width: clamp(40rem, 100%, 90rem);
        margin-inline: auto;
    }
    
    nav {
        padding-block: 1rem;
    }

    ul {
        display: flex;
        flex-direction: row;
        align-items: center;
        gap: 1rem;

        width: 100%;
    }

    h1 {
        margin: 0;
    }

    div.space {
        flex-grow: 1
    }

</style>
