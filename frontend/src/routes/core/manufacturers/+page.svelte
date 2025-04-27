<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import { goto } from "$app/navigation";
    import {onMount} from "svelte";
    import {fetchAllManufacturers} from "$lib/api.js";

    let manufacturers = [];
    onMount(async () => {
        manufacturers = await fetchAllManufacturers();
    })

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

    function addManufacturer() {
        alert('Add Manufacturer functionality coming soon!');
    }
</script>

<DataTable
        title="Manufacturers List"
        data={manufacturers}
        columns={columns}
        onRowClick={handleRowClick}
        onAdd={addManufacturer}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>
