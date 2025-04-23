<script>
    export let title = 'Data Table';
    export let data = [];
    export let columns = [];
    export let onRowClick;
    export let onAdd;
    export let onEdit;
    export let onDelete;
</script>

<h2>{title}</h2>

{#if onAdd}
    <button class="add-btn" on:click={onAdd}>Add</button>
{/if}

<table>
    <thead>
    <tr>
        {#each columns as { name }}
            <th>{name}</th>
        {/each}
        {#if onEdit || onDelete}
            <th>Actions</th>
        {/if}
    </tr>
    </thead>
    <tbody>
    {#each data as item}
        <tr>
            {#each columns as { key, clickable }}
                <td>
                    {#if clickable && onRowClick}
                        <button
                                class="component-name"
                                on:click={(e) => {
                                    e.stopPropagation();
                                    onRowClick(item);
                                }}
                        >
                            {item[key]}
                        </button>
                    {:else}
                        {item[key]}
                    {/if}
                </td>
            {/each}
            {#if onEdit || onDelete}
                <td>
                    {#if onEdit}
                        <button on:click={() => onEdit(item)}>Edit</button>
                    {/if}
                    {#if onDelete}
                        <button on:click={() => onDelete(item)}>Delete</button>
                    {/if}
                </td>
            {/if}
        </tr>
    {/each}
    </tbody>
</table>
