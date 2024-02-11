<script>
	import Price from "../component/Price.svelte";
	import TagList from "../component/TagList.svelte"
	import ProductQuantity from "./ProductQuantity.svelte";
	import ProductTitle from "./ProductTitle.svelte";
	import ProductScore from "./ProductScore.svelte";

	export let product
	const { name, category, price, quantity, productScore, tags } = product;
	const IMAGE_NOT_FOUND_URL = "/images/image-not-found.png";
</script>

<div class="product">
	<div class="product__img">
		<img src={IMAGE_NOT_FOUND_URL} alt={name} />
	</div>
	<div class="product__content">
		<ProductTitle name={name} category={category} />
		<ProductScore score={productScore} />
		<ProductQuantity quantity={quantity} />
		<TagList tags={tags} />
		<Price price={price} />
	</div>

	<div class="product__cart">
		<slot />
	</div>
</div>

<style>
	.product {
		display: grid;
		grid-template-columns: minmax(min-content, max-content) auto minmax(8rem, 10rem);
		gap: 1rem;

		padding: 1rem;
		border: 1px solid gray;
	}

    .product__content {
        display: flex;
		flex-direction: column;
		gap: 1ch;
    }

	.product__cart {
		align-self: center;
	}

    .product__img {
		/* TODO: fix this mess */
        object-fit: cover;
        width: 12rem;
        height: 12rem;
    }

</style>
