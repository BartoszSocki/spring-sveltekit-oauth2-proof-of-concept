<li>
    <slot />
</li>

<style>
	li {
		--text: #a8d5ed;
		display: inline-flex;
		gap: 1ch;
		align-items: center;
		padding: 1ch 1.5ch;
		border-radius: 4rem;
		border: 2px solid var(--text);
		font-size: 0.8rem;
		list-style: none;
		color: var(--text);
	}
</style>