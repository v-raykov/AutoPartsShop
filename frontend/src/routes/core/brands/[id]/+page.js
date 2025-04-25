import { error } from '@sveltejs/kit';

let brands = [
    { id: 1, name: 'Dimitur', cars:['Toyota', 'Honda', 'Ford'] },
    { id: 2, name: 'Domozetov', cars: ['Toyota', 'Nissan'] }
];

export function load({ params }) {
    const id = parseInt(params.id);
    const brand = brands.find(p => p.id === id);

    if (!brand) {
        throw error(404, 'Part not found');
    }

    return {
        brand
    };
}

