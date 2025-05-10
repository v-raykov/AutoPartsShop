<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import {goto} from "$app/navigation";
    import {onMount} from "svelte";
    import {fetchAllCars, fetchBrandById, fetchManufacturerById, deleteCar} from "$lib/api.js";

    let cars = [];

    onMount(async () => {
        const rawCars = await fetchAllCars();

        cars = await Promise.all(rawCars.map(async (car) => {
            const [brand, manufacturer] = await Promise.all([
                fetchBrandById(car.brandId),
                fetchManufacturerById(car.manufacturerId)
            ]);

            return {
                ...car,
                brandName: brand.name,
                manufacturerName: manufacturer.name
            };
        }));
    });

    const columns = [
        {name: 'Model', displayKey: 'model', valueKey: 'id', clickable: true, linkType: 'cars'},
        {name: 'Brand', displayKey: 'brandName', valueKey: 'brandId', clickable: true, linkType: 'brands'},
        {name: 'Manufacturer', displayKey: 'manufacturerName', valueKey: 'manufacturerId', clickable: true, linkType: 'manufacturers'},
        {name: 'Year', displayKey: 'year'}
    ];

    function handleCellClick(car, column) {
        if (column.linkType) {
            const id = car[column.valueKey];
            goto(`/core/${column.linkType}/${id}`);
        }
    }

    function handleEdit(car) {
        alert(`Edit car ${car.model} functionality coming soon!`);
    }

    async function handleDelete(car) {
        if (confirm(`Delete ${car.model}?`)) {
            await deleteCar(car.id);
            cars = cars.filter(c => c.id !== car.id);
        }
    }

    function addCar() {
        goto('/core/cars/create');
    }
</script>

<DataTable
        title="Cars List"
        data={cars}
        columns={columns}
        onCellClick={handleCellClick}
        onAdd={addCar}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>
