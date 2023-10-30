<script>
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
				{#each tags as tag}
					<li class="tag">{tag}</li>
				{/each}
			</ul>
		</div>
		<div class="price">
			{price.amount.toFixed(2)}
			{price.currency}
		</div>
	</div>
</div>

<style>
	div.wrapper {
		display: flex;
		flex-direction: row;
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

	li.tag {
		--text: #a8d5ed;
		display: inline-block;
		padding: 1ch 1.5ch;
		border-radius: 4rem;
		border: 2px solid var(--text);
		font-size: 0.8rem;
		list-style: none;
		color: var(--text);
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

	h4 {
		margin: 0;
	}
</style>
