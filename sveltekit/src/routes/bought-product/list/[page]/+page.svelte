<script>
    import BoughtProduct from '$lib/bought-product/BoughtProduct.svelte'
    export let data;

    $: boughtProducts = data.content
</script>

<div>
    {#if boughtProducts}
        <ul>
            {#each boughtProducts as boughtProduct (boughtProduct.id)}
                <li>
                    <BoughtProduct boughtProduct={boughtProduct}>
                        <a href="/product/{boughtProduct.productId}/review">add review</a>
                    </BoughtProduct>
                </li>
            {/each}
        </ul>
    {:else}
        <span>there is no bought products</span>
    {/if}
</div>

<style>
    ul {
        display: flex;
        flex-direction: column;
        gap: 1ch;
        padding-left: 0;
    }

    li {
        list-style: none;
    }
</style>