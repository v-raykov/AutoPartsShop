<script>
    export let title = 'Data Table';
    export let data = [];
    export let columns = [];
    export let onCellClick;
    export let onAdd;
    export let onEdit;
    export let onDelete;
    export let onCarClick;
</script>

<h2>{title}</h2>

{#if onAdd}
    <button class="add-btn" on:click={onAdd}>Add</button>
{/if}

<table>
    <thead>
    <tr>
        {#each columns as {name}}
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
            {#each columns as column}
                <td>
                    {#if column.clickable && onCellClick}
                        <button
                                class="component-name"
                                on:click={(e) => {
                                    e.stopPropagation();
                                    onCellClick(item, column);
                                }}
                        >
                            {item[column.displayKey || column.key]}
                        </button>
                    {:else if (Array.isArray(item[column.key]))}
                        {#each item[column.key] as car, i (car.id)}
                            <button
                                    class="component-name car-id-btn"
                                    on:click={(e) => {
                                        e.stopPropagation();
                                        onCarClick(car.id);
                             }}>
                                {car.model ?? car}
                            </button>
                            {#if i < item[column.key].length - 1},{/if}
                        {/each}

                    {:else}
                        {item[column.displayKey || column.key]}
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
