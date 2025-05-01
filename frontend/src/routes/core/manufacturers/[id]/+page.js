import { error } from '@sveltejs/kit';
import { fetchManufacturerById, fetchCarsByManufacturerId } from '$lib/api.js';

export async function load({ params }) {
    const id = params.id;
    const manufacturer = await fetchManufacturerById(id);

    if (!manufacturer) {
        throw error(404, 'Manufacturer not found');
    }

    const cars = await fetchCarsByManufacturerId(id);

    return {
        manufacturer,
        cars
    };
}
