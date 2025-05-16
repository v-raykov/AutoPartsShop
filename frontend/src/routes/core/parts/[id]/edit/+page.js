import {
    fetchPartById,
    fetchCarsByPartId,
    fetchAllCars,
    fetchAllCategories
} from '$lib/api.js';
import { error } from '@sveltejs/kit';

export async function load({ params }) {
    const partId = params.id;
    const part = await fetchPartById(partId);
    if (!part) throw error(404, 'Part not found');

    const compatibleCars = await fetchCarsByPartId(partId);
    const allCars = await fetchAllCars();
    const categories = await fetchAllCategories();

    return {
        partId,
        part,
        compatibleCars,
        allCars,
        categories
    };
}


