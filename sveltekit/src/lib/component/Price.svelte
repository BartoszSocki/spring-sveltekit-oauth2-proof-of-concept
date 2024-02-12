<script>
    export let price;
</script>

<div class="price">
    <span>{price.amount.toFixed(2)}</span>
    <span>{price.currency}</span>
</div>

<style>
	.price {
		color: #fff;
	}
</style>