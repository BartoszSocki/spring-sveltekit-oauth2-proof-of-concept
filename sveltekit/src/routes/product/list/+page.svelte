<script>
    export let data;

    $: ({SearchProducts} = data);
    $: console.log($SearchProducts.data.searchProducts)
</script>

<section>
    hello this is the product list
    {$SearchProducts.data.searchProducts}
</section>