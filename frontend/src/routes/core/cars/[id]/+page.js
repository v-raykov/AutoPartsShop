import { error } from '@sveltejs/kit';
import {fetchBrandById, fetchCarById, fetchManufacturerById} from "$lib/api.js";

export async function load({params}) {
    const id = params.id;
    const car = await fetchCarById(id);
    const manufacturer = await fetchManufacturerById(car.manufacturerId);
    const brand = await fetchBrandById(car.brandId);

    if (!car) {
        throw error(404, 'Car not found');
    }

    return {
        car, manufacturer, brand
    };
}
