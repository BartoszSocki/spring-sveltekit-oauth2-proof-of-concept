<script>
	import EditableTagList from "../component/EditableTagList.svelte";
    export let searchParams;

    function updateFunc(tags) {
        searchParams.update(curr => {
            return {
                ...curr,
                tags
            }
        })
    }

</script>

<fieldset>
    <legend>Tags</legend>
    <EditableTagList tags={$searchParams['tags']} updateFunc={updateFunc} />
</fieldset>

<style>
    fieldset {
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-template-rows: auto;
        gap: 1rem;
    }
</style>