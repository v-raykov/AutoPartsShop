<script>
    import { onMount } from 'svelte';
    import {
        fetchAllBrands,
        fetchAllManufacturers,
        fetchAllParts,
        updateCar
    } from '$lib/api.js';
    import { goto } from '$app/navigation';
    import "../../../style.css";

    export let data;

    let car = data?.car;
    let brands = [];
    let manufacturers = [];
    let parts = [];

    let model = '';
    let year = '';
    let selectedBrandId = '';
    let selectedManufacturerId = '';
    let selectedPartIds = [];

    let newBrandName = '';
    let isCreatingBrand = false;

    onMount(async () => {
        // Fetch the necessary data for editing
        brands = await fetchAllBrands();
        manufacturers = await fetchAllManufacturers();
        parts = await fetchAllParts();

        // Prepopulate the form with the car's existing data
        if (car) {
            selectedBrandId = car.brandId;
            selectedManufacturerId = car.manufacturerId;
            selectedPartIds = car.partIds;
            model = car.model;
            year = car.year;
        }
    });

    // Toggle the ability to create a new brand
    function toggleBrandCreation() {
        isCreatingBrand = !isCreatingBrand;
        if (isCreatingBrand) {
            selectedBrandId = '';
        }
    }

    // Toggle part selection for the car
    function togglePartSelection(partId) {
        if (selectedPartIds.includes(partId)) {
            selectedPartIds = selectedPartIds.filter(id => id !== partId);
        } else {
            selectedPartIds = [...selectedPartIds, partId];
        }
    }

    // Create a new brand if needed
    async function createBrand(name) {
        const brand = await addBrand(name);
        brands.push(brand);
        return brand.id;
    }

    // Submit the car's updated data
    async function handleSubmit() {
        const brandId = isCreatingBrand
            ? await createBrand(newBrandName)
            : selectedBrandId;

        const carData = {
            id: car.id, // Include the car ID for updating
            model,
            year: parseInt(year),
            brandId,
            manufacturerId: selectedManufacturerId,
            partIds: selectedPartIds
        };

        await updateCar(carData);
        await goto('/core/cars');
    }

    // Open a new tab to create a manufacturer
    function openManufacturerTab() {
        window.open('/core/manufacturers/create', '_blank');
    }
</script>

<div class="container">
    <div class="form-section">
        <form on:submit|preventDefault={handleSubmit}>
            <!-- Car Model -->
            <div class="form-group">
                <label for="model">Car Model:</label>
                <input id="model" type="text" bind:value={model} required />
            </div>

            <!-- Car Year -->
            <div class="form-group">
                <label for="year">Year:</label>
                <input id="year" type="number" bind:value={year} required min="1886" max={new Date().getFullYear() + 1} />
            </div>

            <!-- Brand Selection -->
            <div class="form-group">
                <label for="brand">Brand:</label>
                {#if isCreatingBrand}
                    <input id="brand" type="text" placeholder="New brand name" bind:value={newBrandName} required />
                    <div class="button-container">
                        <button type="button" class="component-name small-button" on:click={toggleBrandCreation}>Cancel</button>
                    </div>
                {:else}
                    <select id="brand" bind:value={selectedBrandId} required>
                        <option value="" disabled>Select a brand</option>
                        {#each brands as brand}
                            <option value={brand.id} selected={brand.id === selectedBrandId}>{brand.name}</option>
                        {/each}
                    </select>
                    <div class="button-container">
                        <button type="button" class="component-name small-button" on:click={toggleBrandCreation}>Create New Brand</button>
                    </div>
                {/if}
            </div>

            <!-- Manufacturer Selection -->
            <div class="form-group">
                <label for="manufacturer">Manufacturer:</label>
                <select id="manufacturer" bind:value={selectedManufacturerId} required>
                    <option value="" disabled>Select a manufacturer</option>
                    {#each manufacturers as manufacturer}
                        <option value={manufacturer.id} selected={manufacturer.id === selectedManufacturerId}>{manufacturer.name}</option>
                    {/each}
                </select>
                <div class="button-container">
                    <button type="button" class="component-name small-button" on:click={openManufacturerTab}>Create New Manufacturer</button>
                </div>
            </div>

            <!-- Parts Selection -->
            <div class="form-group">
                <label for="parts">Parts:</label>
                <div>
                    {#each selectedPartIds as partId, i}
                        {#if parts.find(p => p.id === partId)}
                            <button
                                    type="button"
                                    class="component-name"
                                    on:click={() => togglePartSelection(partId)}
                            >
                                {parts.find(p => p.id === partId)?.name}
                            </button>{#if i < selectedPartIds.length - 1}, {/if}
                        {/if}
                    {/each}
                </div>
            </div>

            <button type="submit">Update Car</button>
        </form>
    </div>

    <!-- Available Parts -->
    <div>
        <h3 class="parts-label">Available Parts</h3>
        <div class="available-parts">
            {#each parts as part}
                {#if !selectedPartIds.includes(part.id)}
                    <button
                            type="button"
                            class="available-part"
                            on:click={() => togglePartSelection(part.id)}
                    >
                        <span>{part.name}</span>
                        <a class="info-link" href={`/core/parts/${part.id}`} target="_blank" rel="noopener noreferrer">Info</a>
                    </button>
                {/if}
            {/each}
        </div>
    </div>

</div>
