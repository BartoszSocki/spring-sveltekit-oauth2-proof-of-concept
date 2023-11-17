<script>
	import FiveStarScore from "../component/FiveStarScore.svelte"
	import Price from "../component/Price.svelte";
	import TagList from "../component/TagList.svelte";
	import ProductQuantity from "../component/ProductQuantity.svelte";
	import ProductTitle from "../component/ProductTitle.svelte";

	export let product;

	const { name, category, price, quantity, productScore, tags } = product;
</script>

<div class="wrapper">
	<div class="img">
		<img src="/images/image-not-found.png" alt={name} />
	</div>
	<div class="content">
		<ProductTitle name={name} category={category} />

		<div class="title">
			{#if productScore.averageScore !== null}
				<div class="product-score">
					<FiveStarScore score={productScore.averageScore} />
					<span class="reviews-count">({productScore.reviewsCount})</span>
				</div>
			{:else}
				<div class="product-score"><i>No Reviews</i></div>
			{/if}
		</div>

		<ProductQuantity quantity={quantity} />
		<TagList tags={tags} />
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

	div.product-score {
		font-size: 0.8rem;
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.add-to-cart {
		align-self: center;
	}
</style>
