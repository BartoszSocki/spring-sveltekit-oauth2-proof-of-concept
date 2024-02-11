<script>
    import FiveStarScore from "../component/FiveStarScore.svelte";
    export let score;
</script>

{#if score !== null && score !== undefined}
    <div class="product-score">
        <FiveStarScore score={score.averageScore} />
        <span class="reviews-count">({score.reviewsCount})</span>
    </div>
{:else}
    <div class="product-score">
        <i>No Reviews</i>
    </div>
{/if}

<style>
	.product-score {
		font-size: 0.8rem;
		display: flex;
		flex-direction: row;
		align-items: center;
	}
</style>