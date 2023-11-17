<script>
    import { isSimpleString } from "../util/validation.js";
    import Tag from "./Tag.svelte";

    export let tags
    export let updateFunc
    export let placeholder = 'Tag...';

    $: newTag = '';
    $: isTagValid = isSimpleString(newTag)

    function addTag(e) {
        e.preventDefault()
        if (newTag === '') {
            return
        }

        if (tags.includes(newTag)) {
            newTag = ''
            return
        }

        tags.push(newTag)
        newTag = ''
        updateFunc(tags)
    }

    function removeTag(tag) {
        tags = tags.filter(t => t !== tag)
        updateFunc(tags)
    }

    function removeAll(e) {
        e.preventDefault()
        newTag = ''
        updateFunc([])
    }
</script>

<div>
    <ul>
        {#if tags}
            {#each tags as tag}
                <Tag>
                    <span>{tag}</span> 
                    <button class="close" on:click={(e) => {
                        e.preventDefault()
                        removeTag(tag)
                    }}>X</button>
                </Tag>
            {/each}
        {/if}
    </ul>
    <input type="text" pattern="[\w\d\s]+" bind:value={newTag} placeholder={placeholder}>
    <button on:click={addTag} disabled={!isTagValid}>Add New Tag</button>
    <button on:click={removeAll} disabled={tags && tags.length === 0}>Remove All</button>
</div>

<style>
    div {
        display: grid;
        grid-template-columns: 3fr 1fr 1fr;
        grid-template-rows: auto;
        gap: 1rem;
    }

    span {
        white-space: nowrap;
    }

    button.close {
        font-size: 1rem;
        padding: 0;
        margin: 0;
        background: none;
        border: none;
        font-size: inherit;
    }

    button.close:hover {
        color: #a8d5ed;
    }

    ul {
        grid-column: 1 / -1;
        display: flex;
        flex-wrap: wrap;
        flex-direction: row;
        gap: 1ch;
        margin-bottom: 0;
        padding-left: 0;
    }
    
    input {
        margin-bottom: 0;
    }

	input[type="text"]:not(:placeholder-shown):invalid {
		border-color: red;
	}
</style>