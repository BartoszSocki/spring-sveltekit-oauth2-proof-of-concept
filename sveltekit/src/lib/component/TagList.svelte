<script>
    import Tag from "./Tag.svelte";
    export let tags;
</script>

<ul>
    {#if tags}
        {#each tags as tag (tag)}
            <Tag>{tag}</Tag>
        {/each}
    {/if}
</ul>

<style>
    ul {
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: row;
        gap: 1ch;
    }
</style>