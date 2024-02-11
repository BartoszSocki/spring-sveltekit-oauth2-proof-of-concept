<script>
    export let href;
</script>

<a href={href}>
    <slot />
</a>

<style>
    a {
        color: var(--link-primary);
    }

    a:hover {
        color: var(--link-primary-hover);
    }
</style>

