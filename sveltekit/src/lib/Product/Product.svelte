<script>
	import Tag from "./Tag.svelte";

	export let product;

	const { name, category, price, quantity, productScore, tags } = product;
</script>

<div class="wrapper">
	<div class="img">
		<img src="/images/image-not-found.png" alt={name} />
	</div>
	<div class="content">
		<div class="title">
			<span>
				<span class="title">{name}</span>
				{category}
			</span>
			{#if productScore.averageScore !== null}
				<div class="product-score">
					<span class="stars" style="--rating: {productScore.averageScore}" />
					<span class="reviews-count">({productScore.reviewsCount})</span>
				</div>
			{:else}
				<div class="product-score"><i>No Reviews</i></div>
			{/if}
			<!-- </span> -->
		</div>
		<div class="quantity">
			{#if quantity > 0}
				<div>In Stock</div>
			{:else}
				<div>Out Of Stock</div>
			{/if}
		</div>
		<div class="tags">
			<ul>
				{#each tags as tag (tag)}
					<Tag>{tag}</Tag>
				{/each}
			</ul>
		</div>
		<div class="price">
			{price.amount.toFixed(2)}
			{price.currency}
		</div>
	</div>

	<div class="add-to-cart">
		<slot />
	</div>
</div>

<style>
	div.wrapper {
		/* display: flex;
		flex-direction: row; */
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

	ul {
		padding-left: 0;
		display: flex;
		flex-direction: row;
		gap: 1ch;
	}

	span.title {
		font-size: 1.4rem;
		font-weight: bold;
		color: #fff;
	}

	/* span.reviews-count {
        font-size: 0.8rem;
    } */

	div.product-score {
		font-size: 0.8rem;
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	:root {
		--star-size: 1rem;
		--star-color: #fff;
		--star-background: #fc0;
	}

	.stars {
		--percent: calc(var(--rating) / 5 * 100%);

		display: inline-block;
		font-size: var(--star-size);
		font-family: Times;
		line-height: 1;
	}

	.stars::before {
		content: '★★★★★';
		letter-spacing: 3px;
		background: linear-gradient(
			90deg,
			var(--star-background) var(--percent),
			var(--star-color) var(--percent)
		);
		-webkit-background-clip: text;
		-webkit-text-fill-color: transparent;
	}

	.price {
		color: #fff;
	}

	.add-to-cart {
		align-self: center;
	}
</style>
