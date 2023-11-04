<script>
    export let filterParams;
    export let isValid;
    
    const positiveIntegerRegex = /^[0-9]\d*$/

    function isInputPositiveInteger(value) {
        return value === undefined 
            || value === null
            || value === ""
            || positiveIntegerRegex.test(value)        
    }

    $: isFirstInputValid = isInputPositiveInteger(filterParams['priceFrom'])
    $: isSecondInputValid = isInputPositiveInteger(filterParams['priceTo'])
    $: isDataValid = isFirstInputValid && isSecondInputValid
    $: isValid.update(current => {
        return {
            ...current,
            priceFilter: isDataValid
        }
    })

</script>

<form>
    <label class={!isFirstInputValid ? "error" : ""}>
        Price from
        <input bind:value={filterParams['priceFrom']} />
        <span>values is not a number</span>
    </label>
    <label class={!isSecondInputValid ? "error" : ""}>
        Price to
        <input bind:value={filterParams['priceTo']} />
        <span>values is not a number</span>
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