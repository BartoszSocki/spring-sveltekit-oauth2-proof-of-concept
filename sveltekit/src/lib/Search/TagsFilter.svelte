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

    // $: isDataValid = isInputSimpleString($searchParams['category'])
    // $: isValid.update(current => {
    //     return {
    //         ...current,
    //         priceFilter: isDataValid
    //     }
    // })

    // $: tags = $searchParams['tags']
    // $: console.log('tags', tags)
    // $: tags = ['aaa', 'bbb', 'ccc'];
    $: newTag = '';
    // $: console.log($searchParams['tags'])
    // $: console.log(searchParams)

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
    <input type="text" bind:value={newTag}>
    <button on:click={addTag}>Add new tag</button>
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

    label.error > span {
        visibility: visible;
        color: red;
        font-size: 0.5rem;
    }

    label.error > input {
        border-color: red;
    }

    label.error {
        color: red;
    }

    label > span {
        visibility: hidden;
    }
    
    input {
        margin-bottom: 0;
    }
</style>