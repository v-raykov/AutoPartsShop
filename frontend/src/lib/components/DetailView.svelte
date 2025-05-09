<script>
    import { goto } from "$app/navigation";

    function navigateTo(url) {
        goto(url);
    }

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
                                class="component-name"
                                on:click={() => navigateTo(btn.href)}
                        >
                            {btn.label}
                        </button>
                        {i < field.buttons.length - 1 ? ', ' : ''}
                    {/each}
                {:else if field.isButton && field.href}
                    <button
                            class="component-name"
                            on:click={() => navigateTo(field.href)}
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
