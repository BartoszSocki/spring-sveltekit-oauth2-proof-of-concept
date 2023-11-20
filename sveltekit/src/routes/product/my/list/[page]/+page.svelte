<script>
	import Product from '$lib/product/Product.svelte';
    import { PUBLIC_BACKEND_URL } from '$env/static/public'

    export let data;
    $: products = data.data.searchProducts.content

    function deleteProduct(id) {
        fetch(`${PUBLIC_BACKEND_URL}/product/${id}`, { 
            method: 'DELETE'
        })
    }
</script>

<ul>
    {#if products !== undefined && products !== null}
        {#each products as product (product.id)}
            <li>
                <Product product={product}>
                    <button on:click={() => deleteProduct(product.id)}>delete</button>
                </Product>
            </li>
        {/each}
    {:else}
        <div>wow so empty here</div>
    {/if}
</ul>


<style>
    li {
        list-style: none;
        list-style-position: inside;
    }

    ul {
        padding-left: 0;
        display: flex;
        flex-direction: column;
        gap: 1rem;
    }

    button {
        margin: 0;
        padding: 1rem;
        height: fit-content;
        background: transparent;
        border: 2px solid red;
        color: red;
    }
</style>