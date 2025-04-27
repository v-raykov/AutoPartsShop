<script>
    import DataTable from '$lib/components/DataTable.svelte';
    import '../style.css';
    import {goto} from "$app/navigation";
    import {fetchAllCars} from "$lib/api.js";
    import {onMount} from "svelte";

    let cars = [];
    onMount(async () => {
        cars = await fetchAllCars();
    })
    const columns = [
        { name: 'Model', key: 'model', clickable: true },
        { name: 'Brand', key: 'brandId', clickable: true },
        { name: 'Manufacturer', key: 'manufacturerId', clickable: true },
        { name: 'Year', key: 'year' }
    ];

    function handleRowClick(car) {
        goto(`/core/cars/${car.id}`);
    }

    function handleEdit(car) {
        alert(`Edit car ${car.model} functionality coming soon!`);
    }

    function handleDelete(car) {
        if (confirm(`Delete ${car.model}?`)) {
            cars = cars.filter(c => c.id !== car.id);
        }
    }

    function addCar() {
        const newId = Math.max(...cars.map(c => c.id), 0) + 1;
        cars = [...cars, {
            id: newId,
            brand: 'New Brand',
            model: 'New Model',
            manufacturer: 'New Manufacturer',
            year: new Date().getFullYear()
        }];
    }
</script>

<DataTable
        title="Cars List"
        data={cars}
        columns={columns}
        onRowClick={handleRowClick}
        onAdd={addCar}
        onEdit={handleEdit}
        onDelete={handleDelete}
/>