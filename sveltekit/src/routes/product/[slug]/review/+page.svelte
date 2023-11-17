<script>
    export let data;
    $: fiveStarScore = data.fiveStarScore ?? ''
    $: review = data.review ?? ''

    $: console.log(data)
</script>

<form method="POST">
    <fieldset>
        <label>
            five star score
            <input type="text" name="five-star-score" value={fiveStarScore}>
        </label>
        <label>
            review
            <textarea name="review" value={review}/>
        </label>
    </fieldset>

    <input type="submit" value="submit">
</form>