import { error } from '@sveltejs/kit';
import { fetchManufacturerById } from '$lib/api.js';

export async function load({ params }) {
    const manufacturer = await fetchManufacturerById(params.id);
    if (!manufacturer) {
        throw error(404, 'Manufacturer not found');
    }

    return {
        manufacturer
    };
}
