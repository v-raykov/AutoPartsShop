import { error } from '@sveltejs/kit';
import {fetchPartById} from "$lib/api.js";

export async function load({params}) {
    const id = params.id;
    const part = await fetchPartById(id);

    if (!part) {
        throw error(404, 'Part not found');
    }

    return {
        part
    };
}

