import { error } from '@sveltejs/kit';
import {
    fetchBrandById,
    fetchCarById,
    fetchManufacturerById,
    fetchPartsByCarId
} from '$lib/api.js';

export async function load({ params }) {
    const id = params.id;
    const car = await fetchCarById(id);

    if (!car) {
        throw error(404, 'Car not found');
    }

    const manufacturer = await fetchManufacturerById(car.manufacturerId);
    const brand = await fetchBrandById(car.brandId);
    const parts = await fetchPartsByCarId(id); // New fetch for part data

    return {
        car,
        manufacturer,
        brand,
        parts
    };
}
