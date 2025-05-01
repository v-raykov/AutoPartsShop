import { error } from '@sveltejs/kit';
import { fetchBrandById, fetchCarsByBrandId } from '$lib/api.js';

export async function load({ params }) {
    const id = params.id;
    const brand = await fetchBrandById(id);

    if (!brand) {
        throw error(404, 'Brand not found');
    }

    const cars = await fetchCarsByBrandId(id);

    return {
        brand,
        cars
    };
}

