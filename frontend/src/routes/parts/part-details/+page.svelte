<script context="module" lang="js">
    export async function load({ params }) {
        const { part } = params;
        const parts = [
            { name: 'Brake Pad', priceBuy: 20, priceSell: 35, quantity: 10, compatibleCars: ['Toyota', 'Honda', 'Ford'] },
            { name: 'Oil Filter', priceBuy: 5, priceSell: 10, quantity: 15, compatibleCars: ['Honda', 'Ford'] },
            { name: 'Battery', priceBuy: 50, priceSell: 80, quantity: 5, compatibleCars: ['Ford', 'Chevrolet'] }
        ];

        const partDetails = parts.find(p => p.name.toLowerCase() === part.toLowerCase());

        return {
            props: {
                partDetails
            }
        };
    }
</script>

<script>
    export let partDetails;
</script>

<style>
    .part-details {
        background: #f9f9f9;
        padding: 2rem;
        border-radius: 10px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    }

    h2 {
        color: #4c6ef5;
        margin-bottom: 1rem;
    }

    p {
        font-size: 1.1rem;
        margin-bottom: 1rem;
    }

    .details {
        margin-bottom: 1rem;
    }

    .car-list {
        list-style: none;
        padding-left: 0;
    }

    .car-list li {
        font-size: 1rem;
        margin-bottom: 0.5rem;
    }

    .back-button {
        padding: 0.6rem 1rem;
        background: #3751a2;
        color: white;
        border-radius: 5px;
        text-decoration: none;
        transition: background 0.3s ease;
    }

    .back-button:hover {
        background: #2a3a75;
    }
</style>

<div class="part-details">
    {#if partDetails}
        <h2>{partDetails.name} - Part Details</h2>
        <div class="details">
            <p><strong>Price Buy:</strong> ${partDetails.priceBuy}</p>
            <p><strong>Price Sell:</strong> ${partDetails.priceSell}</p>
            <p><strong>Quantity:</strong> {partDetails.quantity}</p>
        </div>
        <h3>Compatible Cars:</h3>
        <ul class="car-list">
            {#each partDetails.compatibleCars as car}
                <li>{car}</li>
            {/each}
        </ul>
        <a href="/" class="back-button">Back to Parts List</a>
    {:else}
        <p>Part not found.</p>
    {/if}
</div>
