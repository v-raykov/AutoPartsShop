import { fetchPartById, fetchCarsByPartId } from '$lib/api.js';
import { error } from '@sveltejs/kit';

export async function load({ params }) {
    const id = params.id;
    const part = await fetchPartById(id);

    if (!part) {
        throw error(404, 'Part not found');
    }

    const cars = await fetchCarsByPartId(id); // uses part ID, not carIds

    return {
        part,
        cars
    };
}
