<script>
    import './style.css';
    import {fetchAllBrands, fetchAllCars, fetchAllManufacturers, fetchAllParts} from "$lib/api.js";
    import { onMount } from 'svelte';

    let stats = {
        parts: 0,
        cars: 0,
        brands: 0,
        manufacturers: 0
    };

    onMount(async () => {
        stats.parts = (await fetchAllParts()).length;
        stats.cars = (await fetchAllCars()).length;
        stats.brands = (await fetchAllBrands()).length;
        stats.manufacturers = (await fetchAllManufacturers()).length;
    });
</script>

<h2>Dashboard</h2>
<div class="grid">
    <div class="card">🔩 Parts: {stats.parts}</div>
    <div class="card">🚗 Cars: {stats.cars}</div>
    <div class="card">🏷️ Brands: {stats.brands}</div>
    <div class="card">🏭 Manufacturers: {stats.manufacturers}</div>
</div>
