<script>
    import { goto } from '$app/navigation';
    import DataTable from '$lib/components/DataTable.svelte';
    import '../style.css';

    let parts = [
        { name: 'Brake Pad', priceBuy: 20, priceSell: 35, quantity: 10, compatibleCars: ['Toyota', 'Honda', 'Ford'] },
        // ... other parts
    ];

    const columns = [
        { name: 'Name', key: 'name', clickable: true },
        { name: 'Price Buy', key: 'priceBuy' },
        { name: 'Price Sell', key: 'priceSell' },
        { name: 'Quantity', key: 'quantity' }
    ];

    function handleRowClick(value) {
        alert(value)
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