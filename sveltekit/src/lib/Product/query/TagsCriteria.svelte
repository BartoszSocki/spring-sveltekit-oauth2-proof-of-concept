<script>
	import EditableTagList from "../../component/EditableTagList.svelte";
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
        display: flex;
        flex-direction: column;
    }
</style>