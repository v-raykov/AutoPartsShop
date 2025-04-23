<script>
    export let data;
    $: part = data?.part;
</script>

{#if !part}
    <p>Loading part details...</p>
{:else}
    <h1>Part Details: {part.name}</h1>
    <ul>
        <li><strong>Buy Price:</strong> ${part.priceBuy}</li>
        <li><strong>Sell Price:</strong> ${part.priceSell}</li>
        <li><strong>Quantity:</strong> {part.quantity}</li>
        <li><strong>Compatible Cars:</strong> {part.compatibleCars.join(', ')}</li>
    </ul>
{/if}
