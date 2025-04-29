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
            <li>
                <strong>{field.label}:</strong>
                {#if field.isButton && field.value}
                    <button on:click={() => window.location.href = field.href} class="component-name">
                        {field.value}
                    </button>
                {:else}
                    {field.value}
                {/if}
            </li>
        {/each}
    </ul>
{/if}
