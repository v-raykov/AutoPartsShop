<script>
    import { goto } from '$app/navigation';

    let parts = [
        { name: 'Brake Pad', priceBuy: 20, priceSell: 35, quantity: 10, compatibleCars: ['Toyota', 'Honda', 'Ford'] },
        { name: 'Oil Filter', priceBuy: 5, priceSell: 10, quantity: 15, compatibleCars: ['Honda', 'Ford'] },
        { name: 'Battery', priceBuy: 50, priceSell: 80, quantity: 5, compatibleCars: ['Ford', 'Chevrolet'] }
    ];

    function addPart() {
        parts = [...parts, { name: 'New Part', priceBuy: 0, priceSell: 0, quantity: 0, compatibleCars: [] }];
    }

    function editPart(index) {
        alert(`Edit part at index ${index}`);
    }

    function deletePart(index) {
        parts = parts.filter((_, i) => i !== index);
    }

    function viewPartDetails(name) {
        goto(`/part-details/${name}`);
    }
</script>

<style>
    .add-btn {
        padding: 0.8rem 1.2rem;
        font-size: 1.2rem;
        background-color: #4caf50;
        color: white;
        border-radius: 8px;
        margin-bottom: 1.5rem;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        transition: background 0.3s ease, transform 0.2s ease;
    }
    .add-btn:hover {
        background-color: #45a049;
        transform: translateY(-2px);
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 2rem;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
    }
    th, td {
        padding: 1rem;
        text-align: left;
        font-size: 1rem;
    }
    th {
        background-color: #6a7bff;
        color: white;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #f1f3f6;
        cursor: pointer;
    }

    button {
        padding: 0.6rem 1rem;
        font-size: 1rem;
        border: none;
        background: #4c6ef5;
        color: white;
        border-radius: 5px;
        cursor: pointer;
        transition: background 0.3s ease, transform 0.2s ease;
        margin-left: 0.5rem;
    }
    button:hover {
        background: #3751a2;
        transform: translateY(-2px);
    }

    .part-name-btn {
        font-size: 1rem;
        color: black;
        background-color: transparent;
        border: none;
        cursor: pointer;
        padding: 0;
        text-decoration: none;
        transition: transform 0.2s ease, color 0.3s ease;
    }

    .part-name-btn:hover {
        color: white;
        transform: translateY(-2px);
    }

</style>

<h2>Parts List</h2>
<button class="add-btn" on:click={addPart}>Add Part</button>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Price Buy</th>
        <th>Price Sell</th>
        <th>Quantity</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    {#each parts as part, i}
        <tr>
            <td>
                <button class="part-name-btn" on:click={() => viewPartDetails(part.name)}>{part.name}</button>
            </td>
            <td>${part.priceBuy}</td>
            <td>${part.priceSell}</td>
            <td>{part.quantity}</td>
            <td>
                <button on:click={() => editPart(i)}>Edit</button>
                <button on:click={() => deletePart(i)}>Delete</button>
            </td>
        </tr>
    {/each}
    </tbody>
</table>
