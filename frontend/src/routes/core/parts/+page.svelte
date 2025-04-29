<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import {goto} from "$app/navigation";
    import {fetchAllParts} from "$lib/api.js";
    import {onMount} from "svelte";

    let parts = [];
    onMount(async () => {
        parts = await fetchAllParts();
    })

    const columns = [
        {name: 'Name', key: 'name', clickable: true},
        {name: 'Price Buy', key: 'priceBuy'},
        {name: 'Price Sell', key: 'priceSell'},
        {name: 'Quantity', key: 'quantity'}
    ];

    function handleRowClick(part) {
        goto(`/core/parts/${part.id}`);
    }

    function handleEdit(part) {
        alert('Edit functionality coming soon!');
    }

    function handleDelete(part) {
        alert('Delete functionality coming soon!');
    }

    function addPart() {
        alert('Add part functionality coming soon!');
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
