<script>
    export let product

    const score = product.productScore.averageScore;
</script>

<div class="wrapper">
    <div class="score">
        <i>
            {score ?? 'no reviews'}
        </i>
    </div>
    <span>{product.name}</span>
</div>

<style>
    div.wrapper {
        padding: 1rem;
        border: 1px solid gray;
    }
</style>