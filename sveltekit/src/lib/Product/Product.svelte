<script>
    export let product
    console.log({product})
</script>

<section>
    <p>this is product</p>
    <p>{product.name}</p>
    <!-- <p>{product.score}</p> -->
</section>

<style>
    section {
        padding: 1rem;
        border: 1px solid gray;
    }
</style>