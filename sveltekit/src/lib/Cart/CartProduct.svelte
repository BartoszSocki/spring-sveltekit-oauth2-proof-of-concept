<script>
	export let product;
    const { name, price } = product;
</script>

<div class="container">
    <!-- <div class="img"> -->
		<img src="/images/image-not-found.png" alt={name} />
	<!-- </div>  -->
    <span class="name">{name}</span>
    <span>{price.amount} {price.currency}</span>
</div>

<style>
    img {
        display: inline-block;
        object-fit: cover;
        width: 3rem;
        height: 3rem;
    }

    .name {
        color: white;
    }

    .container {
        display: flex;
        flex-direction: row;
        align-items: center;
        gap: 1rem;
    }
</style>