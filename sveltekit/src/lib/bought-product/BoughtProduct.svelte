<script>
	export let boughtProduct;

    const { name, category, price, date } = boughtProduct;
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
		</div>
        <div>
            <i>
                bought at: {date}
            </i>
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

	:root {
		--star-size: 1rem;
		--star-color: #fff;
		--star-background: #fc0;
	}

	.price {
		color: #fff;
	}
</style>
