<script>
    export let product

    const { category, price, quantity, productScore } = product;
</script>

<div class="wrapper">
    <h4>{product.name}</h4>
    <div class="category">
        {category}
    </div>
    <div class="price">
        {price.amount}
        {price.currency}
    </div>
    <div class="quantity">
        {quantity}
    </div>
    <div class="score">
        {productScore.averageScore !== null ? productScore.averageScore.toFixed(2) : 'no reviews'}
    </div>
</div>

<style>
    div.wrapper {
        display: grid;
        flex-direction: row;
        gap: 1ch;
        align-items: center;

        padding: 1rem;
        border: 1px solid gray;
    }

    h4 {
        margin: 0;
    }
</style>