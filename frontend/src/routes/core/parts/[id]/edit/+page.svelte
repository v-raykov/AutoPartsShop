<script>
    import {
        updatePart,
        changePartQuantity,
        addCarToPart,
        removeCarFromPart
    } from '$lib/api.js';
    import { goto } from '$app/navigation';
    import "../../../style.css";

    export let data;

    let part = data?.part;
    let compatibleCars = data?.compatibleCars || [];
    let allCars = data?.allCars || [];
    let categories = data?.categories || [];

    let name = part?.name || '';
    let category = part?.category || '';
    let priceBuy = part?.priceBuy || '';
    let priceSell = part?.priceSell || '';
    let quantityChange = 0;

    function toggleCarSelection(carId) {
        if (compatibleCars.find(c => c.id === carId)) {
            removeCarFromPart(part.id, carId);
            compatibleCars = compatibleCars.filter(c => c.id !== carId);
        } else {
            addCarToPart(part.id, carId);
            compatibleCars = [...compatibleCars, allCars.find(c => c.id === carId)];
        }
    }

    function handleQuantityChange(event) {
        let newValue = event.target.value;

        if (newValue === '') {
            quantityChange = '';
            return;
        }

        if (isNaN(newValue)) {
            event.target.value = quantityChange;
            return;
        }

        newValue = parseInt(newValue);

        if (newValue < -part?.quantity) {
            event.target.value = -part?.quantity;
            quantityChange = -part?.quantity;
        } else {
            quantityChange = newValue;
        }
    }

    async function handleSubmit() {
        const updatedPart = {
            id: part.id,
            name,
            category,
            priceBuy: parseFloat(priceBuy),
            priceSell: parseFloat(priceSell)
        };

        await updatePart(updatedPart);

        if (quantityChange !== 0) {
            await changePartQuantity(part.id, quantityChange);
        }

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
                    {#each categories as category}
                        <option value={category}>{category}</option>
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
                <label for="quantityChange">Change Quantity (±):</label>
                <input
                        id="quantityChange"
                        type="number"
                        value={quantityChange}
                        on:input={handleQuantityChange}
                        step="1"
                />
            </div>

            <div class="form-group">
                <label for="cars">Compatible Cars:</label>
                <div>
                    {#each compatibleCars as car}
                        <button
                                type="button"
                                class="component-name"
                                on:click={() => toggleCarSelection(car.id)}
                        >
                            {car.model} ({car.year})
                        </button>
                    {/each}
                </div>
            </div>

            <button type="submit">Update Part</button>
        </form>
    </div>

    <div>
        <h3 class="parts-label">Available Cars</h3>
        <div class="available-parts">
            {#each allCars as car}
                {#if !compatibleCars.find(c => c.id === car.id)}
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
