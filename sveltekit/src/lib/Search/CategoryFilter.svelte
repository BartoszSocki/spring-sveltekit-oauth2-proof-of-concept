<script>
    export let searchParams;
    export let isValid;
    
    const simpleStringRegex = /^[ \w]+$/

    function isInputSimpleString(value) {
        return value === undefined 
            || value === null
            || value === ""
            || simpleStringRegex.test(value)
    }

    $: isDataValid = isInputSimpleString($searchParams['category'])
    $: isValid.update(current => {
        return {
            ...current,
            priceFilter: isDataValid
        }
    })
</script>

<form>
    <label class={!isDataValid ? "error" : ""}>
        Category name
        <input bind:value={$searchParams['category']} />
        <span>category name contains invalid character</span>
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
    
    input {
        margin-bottom: 0;
    }
</style>