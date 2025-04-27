import { error } from '@sveltejs/kit';
import {fetchBrandById} from "$lib/api.js";

export async function load({params}) {
    const id = params.id;
    const brand = await fetchBrandById(id);

    if (!brand) {
        throw error(404, 'Brand not found');
    }

    return {
        brand
    };
}

