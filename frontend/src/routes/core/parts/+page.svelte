<!-- src/routes/part/+page.svelte -->
<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import { goto } from "$app/navigation";

    let parts = [
        { id: 1, name: 'Brake Pad', priceBuy: 20, priceSell: 35, quantity: 10, compatibleCars: ['Toyota', 'Honda', 'Ford'] },
        { id: 2, name: 'Oil Filter', priceBuy: 5, priceSell: 10, quantity: 30, compatibleCars: ['Toyota', 'Nissan'] }
    ];

    const columns = [
        { name: 'Name', key: 'name', clickable: true },
        { name: 'Price Buy', key: 'priceBuy' },
        { name: 'Price Sell', key: 'priceSell' },
        { name: 'Quantity', key: 'quantity' }
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
        onRowClick={handleRowClick}
        onAdd={addPart}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>
