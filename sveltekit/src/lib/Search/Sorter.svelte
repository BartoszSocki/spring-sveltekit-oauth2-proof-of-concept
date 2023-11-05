<script>
	export let searchParams;
	// let fieldName = '';
	// let sortDirection = '';
	// $: console.log(fieldName);
</script>

<form>
	<fieldset>
        <legend>Order by</legend>
		<label>
			<input type="radio" bind:group={$searchParams['orderBy']} value="name" />
			Name
		</label>
		<label>
			<input type="radio" bind:group={$searchParams['orderBy']} value="price" />
			Price
		</label>
		<label>
			<input type="radio" bind:group={$searchParams['orderBy']} value="score" />
			Score
		</label>
	</fieldset>

    <fieldset>
        <legend>Direction</legend>
        <label>
            <input type="radio" bind:group={$searchParams['sortDir']} value="ASC" />
            Ascending
        </label>
        <label>
            <input type="radio" bind:group={$searchParams['sortDir']} value="DSC" />
            Descending
        </label>
    </fieldset>
</form>

<style>
	form {
		display: grid;
		grid-template-columns: 1fr 1fr;
		grid-template-rows: repeat(1, 1fr);
		gap: 1rem;
	}

	input {
		margin-bottom: 0;
	}

	label:has(input[type='radio']) {
		grid-column: 1;
	}
</style>
