<script>
    export let quantity
</script>

<div>
    {#if quantity > 0}
        <div>In Stock</div>
    {:else}
        <div>Out Of Stock</div>
    {/if}
</div>