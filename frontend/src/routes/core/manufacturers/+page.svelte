<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import { goto } from "$app/navigation";

    let manufacturers = [
        { id: 1, name: 'Krasimir', address: '123 Main St, Sofia', phoneNumber: '123-456-7890', email: 'dimitur@example.com', cars: ['Toyota', 'Honda', 'Ford'] },
        { id: 2, name: 'Jelezov', address: '456 Elm St, Plovdiv', phoneNumber: '987-654-3210', email: 'domozetov@example.com', cars: ['Toyota', 'Nissan'] }
    ];

    const columns = [
        { name: 'Name', key: 'name', clickable: true },
        { name: 'Address', key: 'address' },
        { name: 'Phone Number', key: 'phoneNumber' },
        { name: 'Email', key: 'email' },
    ];

    function handleRowClick(manufacturer) {
        goto(`/core/manufacturers/${manufacturer.id}`);
    }

    function handleEdit(manufacturer) {
        alert('Edit functionality coming soon!');
    }

    function handleDelete(manufacturer) {
        alert('Delete functionality coming soon!');
    }

    function addPart() {
        alert('Add part functionality coming soon!');
    }
</script>

<DataTable
        title="Manufacturers List"
        data={manufacturers}
        columns={columns}
        onRowClick={handleRowClick}
        onAdd={addPart}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>
