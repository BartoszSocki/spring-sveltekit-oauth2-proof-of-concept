<script>
    import { positiveIntegerRegex } from '$lib/util/validation.js'

    export let criteria;
    export let isValid;

    function isInputPositiveInteger(value) {
        return positiveIntegerRegex.test(value);
    }

    function isInputEmpty(value) {
        return value === undefined 
            || value === null
            || value === "";
    }

    function isInputValid(value) {
        return isInputPositiveInteger(value) || isInputEmpty(value);
    }

    $: isPriceFromValid = isInputValid($criteria['priceFrom'])
    $: isPriceToValid = isInputValid($criteria['priceTo'])
    $: isDataValid = isPriceFromValid && isPriceToValid
    $: isValid.update(current => {
        return {
            ...current,
            priceFilter: isDataValid
        }
    })

</script>

<form>
    <label class={!isPriceFromValid ? "error" : ""}>
        Price from
        <input bind:value={$criteria['priceFrom']} />

        {#if !isPriceFromValid}
        <span>values is not a positive number</span>
        {/if}
    </label>
    <label class={!isPriceToValid ? "error" : ""}>
        Price to
        <input bind:value={$criteria['priceTo']} />

        {#if !isPriceToValid}
        <span>values is not a positive number</span>
        {/if}
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

    input {
        margin-bottom: 0;
    }
</style>