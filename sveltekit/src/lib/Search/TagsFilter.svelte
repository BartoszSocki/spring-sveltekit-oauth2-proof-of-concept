<script>
	import Tag from "$lib/product/Tag.svelte";
    export let searchParams;
    export let isValid;
    
    const simpleStringRegex = /^[ \w]+$/

    function isInputSimpleString(value) {
        return value === undefined 
            || value === null
            || value === ""
            || simpleStringRegex.test(value)
    }


    $: newTag = '';
    $: isDataValid = isInputSimpleString(newTag)
    $: isValid.update(current => {
        return {
            ...current,
            tagsFilter: isDataValid
        }
    })

    function addTag(e) {
        if (newTag === '') {
            return
        }

        searchParams.update(current => {
            let updatedTags = current['tags']
            
            if (!updatedTags.includes(newTag)) {
                updatedTags.push(newTag)
                newTag = ''
            }

            return {
                ...current,
                tags: updatedTags
            }
        })
    }

    function removeTag(tag) {
        searchParams.update(current => {
            let updatedTags = current['tags'].filter(t => t !== tag)

            console.log(updatedTags)

            return {
                ...current,
                tags: updatedTags
            }
        })
    }

</script>

<fieldset>
    <legend>Tags</legend>
    <ul>
        {#if $searchParams['tags']}
            {#each $searchParams['tags'] as tag}
                <Tag>
                    <span>{tag}</span> 
                    <button class="close" on:click={() => removeTag(tag)}>X</button>
                </Tag>
            {/each}
        {/if}
    </ul>
    <input type="text" bind:value={newTag} class={isDataValid ? '' : 'error'}>
    <button on:click={addTag} disabled={!isDataValid}>Add new tag</button>
</fieldset>

<style>
    fieldset {
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-template-rows: auto;
        gap: 1rem;
    }

    legend {
        grid-column: 1 / -1;
        margin-bottom: 1ch;
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

    input.error {
        border-color: red;
    }
    
    input {
        margin-bottom: 0;
    }
</style>