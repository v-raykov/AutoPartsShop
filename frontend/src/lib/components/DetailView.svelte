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

                {#if field.isButtonGroup && field.buttons}
                    {#each field.buttons as btn, i}
                        <button
                                on:click={() => window.location.href = btn.href}
                                class="component-name"
                        >
                            {btn.label}
                        </button>{i < field.buttons.length - 1 ? ', ' : ''}
                    {/each}
                {:else if field.isButton && field.href}
                    <button
                            on:click={() => window.location.href = field.href}
                            class="component-name"
                    >
                        {field.value}
                    </button>
                {:else}
                    {field.value}
                {/if}
            </li>
        {/each}
    </ul>
{/if}
