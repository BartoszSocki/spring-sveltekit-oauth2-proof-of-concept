<script>
	import Price from "../component/Price.svelte";
	import ProductTitle from "../component/ProductTitle.svelte";

	export let boughtProduct;

    const { name, category, price, date } = boughtProduct;
</script>

<div class="wrapper">
	<div class="img">
		<img src="/images/image-not-found.png" alt={name} />
	</div>
	<div class="content">
		<ProductTitle name={name} category={category} />
        <div class="bought-date">
			bought at: {date}
        </div>
		<Price amount={price.amount} currency={price.currency} />
	</div>

	<div class="add-to-cart">
		<slot />
	</div>
</div>

<style>
	div.wrapper {
		display: grid;
		grid-template-columns: minmax(min-content, max-content) auto minmax(8rem, 10rem);
		gap: 1rem;

		padding: 1rem;
		border: 1px solid gray;
	}

	div.content {
		display: flex;
		flex-direction: column;
		gap: 1ch;
	}

	img {
		object-fit: cover;
		width: 12rem;
		height: 12rem;
	}

	.bought-date {
		font-style: italic;
	}
</style>
