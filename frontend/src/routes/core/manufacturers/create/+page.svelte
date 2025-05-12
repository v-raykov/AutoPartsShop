<script>
    import { addManufacturer } from '$lib/api.js';
    import { goto } from '$app/navigation';
    import '../../style.css';

    let name = '';
    let phoneNumber = '';
    let address = '';
    let email = '';

    async function handleSubmit() {
        const manufacturerData = {
            name,
            phoneNumber,
            address,
            email
        };

        await addManufacturer(manufacturerData);
        await goto('/core/manufacturers');
    }
</script>

<div class="container">
    <div class="form-section">
        <form on:submit|preventDefault={handleSubmit}>
            <div class="form-group">
                <label for="name">Manufacturer Name:</label>
                <input id="name" type="text" bind:value={name} required />
            </div>

            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input id="phoneNumber" type="tel" bind:value={phoneNumber} required />
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input id="address" type="text" bind:value={address} required />
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input id="email" type="email" bind:value={email} required />
            </div>

            <button type="submit">Create Manufacturer</button>
        </form>
    </div>
</div>
