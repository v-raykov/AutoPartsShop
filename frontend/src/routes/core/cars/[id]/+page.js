import { error } from '@sveltejs/kit';
import {fetchCarById} from "$lib/api.js";

export async function load({params}) {
    const id = params.id;
    const car = await fetchCarById(id);

    if (!car) {
        throw error(404, 'Car not found');
    }

    return {
        car
    };
}
