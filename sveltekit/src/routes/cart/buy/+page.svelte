<script>
	import { writable } from 'svelte/store';
	import Tag from '$lib/product/Tag.svelte';
	import { simpleStringRegex } from '$lib/util/validation.js'

    function isInputSimpleString(value) {
        return value !== undefined 
            && value !== null
            && value !== ""
            && simpleStringRegex.test(value)
    }

    let country = ''
</script>


<div>
    <!-- cart info -->
    <!-- total price -->
    <!-- can be bought -->
    <form method="POST">
        <fieldset>
            <legend>Shipping Address</legend>
            <label>
                Country
                <input bind:value={country} required pattern="[\w\s]+" name="country" type="text" placeholder="Country..." />
            </label>
            <label>
                City
                <input required pattern="[\w\s]+" name="city" type="text" placeholder="City..." />
            </label>
            <label>
                Postal Code
                <input required pattern="[0-9]{2}-[0-9]{3}" name="postal_code" type="text" placeholder="Postal Code..." />
            </label>
            <label>
                Street
                <input required pattern="[\w\s\d]+" name="image-url" type="text" placeholder="Street..." />
            </label>
        </fieldset>
        <input type="submit" value="buy" />
    </form>
</div>

<style>
	fieldset > legend {
		font-size: 1.2rem;
		color: white;
	}

	ul {
		padding: 0;
		margin-bottom: 0.25rem;
		margin-top: 1.25rem;
		display: flex;
		flex-direction: row;
		gap: 1rem;
	}

	.add-tag {
		display: grid;
        gap: 1rem;
		grid-template-columns: repeat(2, 1fr);
	}

    .add-tag > label {
        grid-column: -1 / 1;
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

	label:has(input:not(:placeholder-shown):invalid) {
		color: red;
	}

	input[type="text"]:not(:placeholder-shown):invalid {
		border-color: red;
	}

</style>
