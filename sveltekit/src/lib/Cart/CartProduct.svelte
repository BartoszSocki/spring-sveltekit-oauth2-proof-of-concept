<script>
	export let product;
    const { name, price } = product;
</script>

<div class="container">
    <div class="img">
		<img src="/images/image-not-found.png" alt={name} />
	</div> 
    <span>{name}</span>
    <span>{price.amount}</span>
    <span>{price.currency}</span>
</div>

<style>
    img {
        object-fit: cover;
        width: 3rem;
        height: 3rem;
    }

    .container {
        display: flex;
        flex-direction: row;
        align-items: center;
        gap: 1rem;
    }
</style>