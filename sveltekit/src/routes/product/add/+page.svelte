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

	const TagsStore = writable([]);

	$: newTag = '';
	$: canTagBeAdded = isInputSimpleString(newTag)

    function handleAddedTag(e) {
        e.preventDefault()
        addTag(newTag)
		newTag = ''
    }

	function handleTagRemoval(e, tag) {
		e.preventDefault()
		removeTag(tag)
	}

	function addTag(name) {
		if (!name) {
			return;
		}

        console.log($TagsStore)

		TagsStore.update((current) => {
			console.log(current)
			if (current.includes(name)) {
				return current;
			}

			return [...current, name];
		});
	}

	function removeTag(tag) {
		TagsStore.update((current) => {
			return current.filter((t) => t !== tag);
		});
	}

	$: tags = $TagsStore.join(',')
	$: console.log(tags)
</script>

<form method="POST">
	<fieldset>
		<legend>General</legend>
		<label>
			Name
			<input required pattern="[\w\s]+" minlength="1" name="name" type="text" placeholder="Name..." />
		</label>
		<label>
			Description
			<input pattern="[\w\s]+" name="description" type="text" placeholder="Description..." />
		</label>
		<label>
			Quantity
			<input required pattern="[0-9]\d*" name="quantity" type="text" placeholder="Quantity..." />
		</label>
	</fieldset>

	<fieldset>
		<legend>Price</legend>

		<label>
			Amount
			<input required pattern="[0-9]\d*" name="amount" type="text" placeholder="Amount..." />
		</label>
		<label>
			Currency
			<input required pattern="[\w]+" name="currency" type="text" placeholder="Currency..." />
		</label>
	</fieldset>

	<fieldset>
		<legend>Tags And Category</legend>

		<label>
			Category
			<input required minlength="1" pattern="[\w ]+" name="category" type="text" placeholder="Category..." />
		</label>

		<input name="tags" value={tags} hidden />
		<div>
			<ul>
				{#if $TagsStore && $TagsStore.length > 0}
					{#each $TagsStore as tag}
						<Tag>
							{tag}
							<button class="close" tabindex="-1" on:click={(e) => handleTagRemoval(e, tag)}>X</button>
						</Tag>
					{/each}
				{/if}
			</ul>
			<div class="add-tag">
				<label for="tag">Tag</label>
				<input pattern="[\w ]+" id="tag" type="text" placeholder="Tag..." bind:value={newTag}/>
				<button on:click={handleAddedTag} disabled={!canTagBeAdded}>add tag</button>
			</div>
		</div>
	</fieldset>
	<input type="submit" value="add product" />
</form>

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

	label:has(input[type="text"]:not(:placeholder-shown):invalid) {
		color: red;
	}

	input[type="text"]:not(:placeholder-shown):invalid {
		border-color: red;
	}

</style>
