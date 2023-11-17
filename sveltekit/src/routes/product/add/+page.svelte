<script>
	import { writable } from 'svelte/store';
	import EditableTagList from '../../../lib/component/EditableTagList.svelte'

	const TagsStore = writable([]);

	function updateFunc(tags) {
		TagsStore.update(curr => tags)
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
		<label>
			Image Url
			<input name="image-url" type="text" placeholder="Image Url..." />
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
		<EditableTagList tags={$TagsStore} updateFunc={updateFunc}/>
	</fieldset>
	<input type="submit" value="add product" />
</form>

<style>
	fieldset > legend {
		font-size: 1.2rem;
		color: white;
	}

	label:has(input:not(:placeholder-shown):invalid) {
		color: red;
	}

	input[type="text"]:not(:placeholder-shown):invalid {
		border-color: red;
	}

</style>
