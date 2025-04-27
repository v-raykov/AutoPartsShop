import { error } from '@sveltejs/kit';
import {fetchManufacturerById} from "$lib/api.js";

export async function load({params}) {
    const id = params.id;
    const manufacturer = await fetchManufacturerById(id);

    if (!manufacturer) {
        throw error(404, 'Manufacturer not found');
    }

    return {
        manufacturer
    };
}
