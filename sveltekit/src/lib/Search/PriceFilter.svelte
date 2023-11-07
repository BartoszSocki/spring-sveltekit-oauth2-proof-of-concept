<script>
    import { positiveIntegerRegex } from '$lib/util/validation.js'

    export let searchParams;
    export let isValid;

    function isInputPositiveInteger(value) {
        return value === undefined 
            || value === null
            || value === ""
            || positiveIntegerRegex.test(value)        
    }

    $: isFirstInputValid = isInputPositiveInteger($searchParams['priceFrom'])
    $: isSecondInputValid = isInputPositiveInteger($searchParams['priceTo'])
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
        <input bind:value={$searchParams['priceFrom']} />
        <span>values is not a number</span>
    </label>
    <label class={!isSecondInputValid ? "error" : ""}>
        Price to
        <input bind:value={$searchParams['priceTo']} />
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