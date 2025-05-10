<script>
    import { onMount } from 'svelte';
    import {
        fetchAllBrands,
        fetchAllManufacturers,
        fetchAllParts,
        addCar,
        addBrand
    } from '$lib/api.js';
    import { goto } from '$app/navigation';
    import "../../style.css"

    let model = '';
    let year = '';
    let brands = [];
    let manufacturers = [];
    let parts = [];

    let selectedBrandId = '';
    let selectedManufacturerId = '';
    let selectedPartIds = [];

    let newBrandName = '';
    let isCreatingBrand = false;

    onMount(async () => {
        brands = await fetchAllBrands();
        manufacturers = await fetchAllManufacturers();
        parts = await fetchAllParts();
    });

    function toggleBrandCreation() {
        isCreatingBrand = !isCreatingBrand;
        if (isCreatingBrand) {
            selectedBrandId = '';
        }
    }

    function togglePartSelection(partId) {
        if (selectedPartIds.includes(partId)) {
            selectedPartIds = selectedPartIds.filter(id => id !== partId);
        } else {
            selectedPartIds = [...selectedPartIds, partId];
        }
    }

    async function createBrand(name) {
        const brand = await addBrand(name);
        brands.push(brand);
        return brand.id;
    }

    async function handleSubmit() {
        const brandId = isCreatingBrand
            ? await createBrand(newBrandName)
            : selectedBrandId;

        const carData = {
            model,
            year: parseInt(year),
            brandId,
            manufacturerId: selectedManufacturerId,
            partIds: selectedPartIds
        };

        await addCar(carData);
        await goto('/core/cars');
    }

    function openManufacturerTab() {
        window.open('/core/manufacturer/create', '_blank');
    }
</script>

<div class="container">
    <div class="form-section">
        <form on:submit|preventDefault={handleSubmit}>
            <div class="form-group">
                <label for="model">Car Model:</label>
                <input id="model" type="text" bind:value={model} required />
            </div>

            <div class="form-group">
                <label for="year">Year:</label>
                <input id="year" type="number" bind:value={year} required min="1886" max={new Date().getFullYear() + 1} />
            </div>

            <div class="form-group">
                <label for="brand">Brand:</label>
                {#if isCreatingBrand}
                    <input id="brand" type="text" placeholder="New brand name" bind:value={newBrandName} required />
                    <div class="button-container">
                        <button type="button" class="component-name small-button" on:click={toggleBrandCreation}>Cancel</button>
                    </div>
                {:else}
                    <select id="brand" bind:value={selectedBrandId} required>
                        <option value="" disabled selected>Select a brand</option>
                        {#each brands as brand}
                            <option value={brand.id}>{brand.name}</option>
                        {/each}
                    </select>
                    <div class="button-container">
                        <button type="button" class="component-name small-button" on:click={toggleBrandCreation}>Create New Brand</button>
                    </div>
                {/if}
            </div>

            <div class="form-group">
                <label for="manufacturer">Manufacturer:</label>
                <select id="manufacturer" bind:value={selectedManufacturerId} required>
                    <option value="" disabled selected>Select a manufacturer</option>
                    {#each manufacturers as m}
                        <option value={m.id}>{m.name}</option>
                    {/each}
                </select>
                <div class="button-container">
                    <button type="button" class="component-name small-button" on:click={openManufacturerTab}>Create New Manufacturer</button>
                </div>
            </div>

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

            <button type="submit">Create Car</button>
        </form>
    </div>

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
