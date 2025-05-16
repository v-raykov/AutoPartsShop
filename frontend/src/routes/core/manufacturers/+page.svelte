<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import {goto} from "$app/navigation";
    import {onMount} from "svelte";
    import {fetchAllManufacturers, deleteManufacturer} from "$lib/api.js";

    let manufacturers = [];
    onMount(async () => {
        manufacturers = await fetchAllManufacturers();
    })

    const columns = [
        {name: 'Name', key: 'name', clickable: true},
        {name: 'Address', key: 'address'},
        {name: 'Phone Number', key: 'phoneNumber'},
        {name: 'Email', key: 'email'},
    ];

    function handleRowClick(manufacturer) {
        goto(`/core/manufacturers/${manufacturer.id}`);
    }

    function handleEdit(manufacturer) {
        goto(`/core/manufacturers/${manufacturer.id}/edit`);
    }

    async function handleDelete(manufacturer) {
        if (confirm(`Are you sure you want to delete ${manufacturer.name}?`)) {
            await deleteManufacturer(manufacturer.id);
            manufacturers = manufacturers.filter(p => p.id !== manufacturer.id);
        }
    }

    function addManufacturer() {
        goto("/core/manufacturers/create");
    }
</script>

<DataTable
        title="Manufacturers List"
        data={manufacturers}
        columns={columns}
        onCellClick={handleRowClick}
        onAdd={addManufacturer}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>
