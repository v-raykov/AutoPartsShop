<script>
    export let item;
    export let titleProp = 'name';
    export let fields = [];
</script>

{#if !item}
    <p>Loading details...</p>
{:else}
    <h1>Details: {item[titleProp]}</h1>
    <ul>
        {#each fields as field}
            <li><strong>{field.label}:</strong> {field.value}</li>
        {/each}
    </ul>
{/if}