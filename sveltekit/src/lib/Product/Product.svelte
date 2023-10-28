<script>
    export let product
    console.log({product})
</script>

<div>
    <p>this is product</p>
    <p>{product.name}</p>
    <!-- <p>{product.score}</p> -->
</div>

<style>
    div {
        padding: 1rem;
        border: 1px solid gray;
    }
</style>