<script>
    import { deletePart } from "$lib/api.js";
    import DataTable from '$lib/components/DataTable.svelte';
    import { goto } from "$app/navigation";
    import { fetchAllParts } from "$lib/api.js";
    import { onMount } from "svelte";

    let parts = [];

    onMount(async () => {
        parts = await fetchAllParts();
    });

    const columns = [
        {name: 'Name', key: 'name', clickable: true},
        {name: 'Price Buy', key: 'priceBuy'},
        {name: 'Price Sell', key: 'priceSell'},
        {name: 'Quantity', key: 'quantity'}
    ];

    function handleRowClick(part) {
        goto(`/core/parts/${part.id}`);
    }

    async function handleDelete(part) {
        if (confirm(`Are you sure you want to delete ${part.name}?`)) {
            await deletePart(part.id);
            parts = parts.filter(p => p.id !== part.id);
        }
    }

    function addPart() {
        alert('Add part functionality coming soon!');
    }

    function handleEdit(part) {
        alert('Edit functionality coming soon!');
    }
</script>

<DataTable
        title="Parts List"
        data={parts}
        columns={columns}
        onCellClick={handleRowClick}
        onAdd={addPart}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>


<DataTable
        title="Parts List"
        data={parts}
        columns={columns}
        onCellClick={handleRowClick}
        onAdd={addPart}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>
