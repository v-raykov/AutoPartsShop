<script>
    import { goto } from '$app/navigation';
    import { updateManufacturer } from '$lib/api.js';
    import '../../../style.css';
    export let data;

    let { manufacturer } = data;

    let name = manufacturer.name;
    let address = manufacturer.address;
    let phoneNumber = manufacturer.phoneNumber;
    let email = manufacturer.email;

    async function handleSubmit() {
        const updated = {
            id: manufacturer.id,
            name,
            address,
            phoneNumber,
            email
        };

        await updateManufacturer(updated);
        await goto('/core/manufacturers');
    }
</script>

<div class="container">
    <div class="form-section">
        <h2>Edit Manufacturer</h2>
        <form on:submit|preventDefault={handleSubmit}>
            <div class="form-group">
                <label for="name">Name:</label>
                <input id="name" type="text" bind:value={name} required />
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input id="address" type="text" bind:value={address} required />
            </div>

            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input id="phoneNumber" type="tel" bind:value={phoneNumber} required />
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input id="email" type="email" bind:value={email} required />
            </div>

            <button type="submit">Update Manufacturer</button>
        </form>
    </div>
</div>
