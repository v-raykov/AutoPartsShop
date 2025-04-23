<script context="module" lang="js">
    import './style.css';

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
        <a href="/frontend/static" class="back-button">Back to Parts List</a>
    {:else}
        <p>Part not found.</p>
    {/if}
</div>
