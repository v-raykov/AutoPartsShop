<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import { goto } from "$app/navigation";
    import {onMount} from "svelte";
    import {fetchAllBrands} from "$lib/api.js";

    let brands = [];
    onMount(async () => {
        brands = await fetchAllBrands();
    })

    const columns = [
        { name: 'Name', key: 'name', clickable: true },
        { name: 'Cars', key: 'carIds' },
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
        onCellClick={handleRowClick}
        onAdd={addPart}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>