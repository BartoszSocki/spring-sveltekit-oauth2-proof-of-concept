<script>
    export let name
    export let category
</script>

<span>
    <span class="name">{name}</span>
    <span class="category">{category}</span>
</span>

<style>
    .name {
        font-size: 1.4rem;
        font-weight: bold;
        color: #fff;
    }

    .category {
        font-style: italic;
    }
</style>