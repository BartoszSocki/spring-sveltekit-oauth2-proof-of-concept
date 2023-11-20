<script>
    export let name
    export let category
    export let deleted = false
</script>

<span>
    <span class="name">{name}</span>
    <span class="category">{category}</span>
    {#if deleted}
        <span class="deleted">(deleted)</span>
    {/if}
</span>

<style>
    .name {
        font-size: 1.4rem;
        font-weight: bold;
        color: #fff;
    }

    .deleted {
        color: red;
    }
</style>