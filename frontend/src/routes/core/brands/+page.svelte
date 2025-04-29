<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import { goto } from "$app/navigation";
    import { onMount } from "svelte";
    import { fetchAllBrands, fetchCarsByBrandId } from "$lib/api.js";

    let brands = [];

    onMount(async () => {
        const rawBrands = await fetchAllBrands();

        brands = await Promise.all(
            rawBrands.map(async brand => {
                const cars = await fetchCarsByBrandId(brand.id);

                return {
                    ...brand,
                    carModels: cars.map(car => ({
                        id: car.id,
                        model: car.model
                    }))
                };
            })
        );
    });

    const columns = [
        { name: 'Name', key: 'name', clickable: true },
        { name: 'Cars', key: 'carModels' } // our custom logic will render this
    ];

    function handleCellClick(item, column) {
        if (column.key === 'name') {
            goto(`/core/brands/${item.id}`);
        }
    }

    function handleCarClick(carId) {
        goto(`/core/cars/${carId}`);
    }

    function handleEdit(brand) {
        alert('Edit brand functionality coming soon!');
    }

    function handleDelete(brand) {
        alert('Delete brand functionality coming soon!');
    }

    function addPart() {
        alert('Add part functionality coming soon!');
    }
</script>

<DataTable
        title="Brands List"
        data={brands}
        columns={columns}
        onCellClick={handleCellClick}
        onCarClick={handleCarClick}
        onAdd={addPart}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>
