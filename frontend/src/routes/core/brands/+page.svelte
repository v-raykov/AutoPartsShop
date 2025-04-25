<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import { goto } from "$app/navigation";

    let brands = [
        { id: 1, name: 'Dimitur', cars:['Toyota', 'Honda', 'Ford'] },
        { id: 2, name: 'Domozetov', cars: ['Toyota', 'Nissan'] }
    ];

    const columns = [
        { name: 'Name', key: 'name', clickable: true },
        { name: 'Cars', key: 'cars' },
    ];

    function handleRowClick(brand) {
        goto(`/core/brands/${brand.id}`);
    }

    function handleEdit(brand) {
        alert('Edit functionality coming soon!');
    }

    function handleDelete(brand) {
        alert('Delete functionality coming soon!');
    }

    function addPart() {
        alert('Add part functionality coming soon!');
    }
</script>

<DataTable
        title="Brands List"
        data={brands}
        columns={columns}
        onRowClick={handleRowClick}
        onAdd={addPart}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>