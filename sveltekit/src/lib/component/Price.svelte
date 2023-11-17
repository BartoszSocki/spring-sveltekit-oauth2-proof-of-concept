<script>
    export let amount;
    export let currency;
</script>

<div class="price">
    <span>{amount.toFixed(2)}</span>
    <span>{currency}</span>
</div>

<style>
	.price {
		color: #fff;
	}
</style>