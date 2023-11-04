<script>
    export let filterParams;
</script>

<form>
    <label>
        Category name
        <input bind:value={filterParams['category']} />
    </label>
</form>

<style>
    form {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 1rem;
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
</style>