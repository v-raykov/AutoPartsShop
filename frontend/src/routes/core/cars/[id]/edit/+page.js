import {
    fetchCarById,
    fetchPartsByCarId,
    fetchAllParts,
    fetchAllBrands
} from '$lib/api.js';
import { error } from '@sveltejs/kit';

export async function load({ params }) {
    const carId = params.id;
    const car = await fetchCarById(carId);
    if (!car) throw error(404, 'Car not found');

    const parts = await fetchPartsByCarId(carId);
    const allParts = await fetchAllParts();
    const brands = await fetchAllBrands();

    return {
        carId,
        car,
        parts,
        allParts,
        brands
    };
}
