<script>
	import Product from '$lib/product/Product.svelte';
    import { PUBLIC_BACKEND_URL } from '$env/static/public'
    import { invalidateAll, goto } from '$app/navigation';
    import { page } from '$app/stores'

    export let data;
    $: products = data.data.searchProducts.content

    async function deleteProduct(id) {
        const response = await fetch(`${PUBLIC_BACKEND_URL}/product/${id}`, { 
            method: 'DELETE'
        })

        const data = await response.json()
        const { status } = data

        if (status === 'success') {
            invalidateAll()
        } else {
            // const href = page.
            goto('/product/my/list/0')
        }

    }
</script>

<ul>
    {#if products !== undefined && products !== null}
        {#each products as product (product.id)}
            <li>
                <Product product={product} deleted={product.isDeleted}>
                    <button disabled={product.isDeleted} on:click={() => deleteProduct(product.id)}>delete</button>
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