<script>
    import { onMount } from 'svelte';
    import {
        fetchAllCategories,
        fetchAllCars,
        addPart
    } from '$lib/api.js';
    import { goto } from '$app/navigation';
    import "../../style.css"

    let name = '';
    let category = '';
    let priceBuy = '';
    let priceSell = '';
    let quantity = '';
    let categories = [];
    let cars = [];
    let selectedCarIds = [];

    onMount(async () => {
        categories = await fetchAllCategories();
        cars = await fetchAllCars();
    });

    function toggleCarSelection(carId) {
        if (selectedCarIds.includes(carId)) {
            selectedCarIds = selectedCarIds.filter(id => id !== carId);
        } else {
            selectedCarIds = [...selectedCarIds, carId];
        }
    }

    async function handleSubmit() {
        const partData = {
            name,
            category,
            priceBuy: parseFloat(priceBuy),
            priceSell: parseFloat(priceSell),
            quantity: parseInt(quantity),
            carIds: selectedCarIds
        };

        await addPart(partData);
        await goto('/core/parts');
    }
</script>

<div class="container">
    <div class="form-section">
        <form on:submit|preventDefault={handleSubmit}>
            <div class="form-group">
                <label for="name">Part Name:</label>
                <input id="name" type="text" bind:value={name} required />
            </div>

            <div class="form-group">
                <label for="category">Category:</label>
                <select id="category" bind:value={category} required>
                    <option value="" disabled>Select a category</option>
                    {#each categories as name}
                        <option value={name}>{name}</option>
                    {/each}
                </select>
            </div>


            <div class="form-group">
                <label for="priceBuy">Price (Buy):</label>
                <input id="priceBuy" type="number" min="0" step="0.01" bind:value={priceBuy} required />
            </div>

            <div class="form-group">
                <label for="priceSell">Price (Sell):</label>
                <input id="priceSell" type="number" min="0" step="0.01" bind:value={priceSell} required />
            </div>

            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input id="quantity" type="number" min="0" bind:value={quantity} required />
            </div>

            <div class="form-group">
                <label for="cars">Compatible Cars:</label>
                <div>
                    {#each selectedCarIds as carId, i}
                        {#if cars.find(c => c.id === carId)}
                            <button
                                    type="button"
                                    class="component-name"
                                    on:click={() => toggleCarSelection(carId)}
                            >
                                {cars.find(c => c.id === carId)?.model}
                            </button>{#if i < selectedCarIds.length - 1}, {/if}
                        {/if}
                    {/each}
                </div>
            </div>

            <button type="submit">Create Part</button>
        </form>
    </div>

    <div>
        <h3 class="parts-label">Available Cars</h3>
        <div class="available-parts">
            {#each cars as car}
                {#if !selectedCarIds.includes(car.id)}
                    <button
                            type="button"
                            class="available-part"
                            on:click={() => toggleCarSelection(car.id)}
                    >
                        <span>{car.model} ({car.year})</span>
                        <a class="info-link" href={`/core/cars/${car.id}`} target="_blank" rel="noopener noreferrer">Info</a>
                    </button>
                {/if}
            {/each}
        </div>
    </div>
</div>
