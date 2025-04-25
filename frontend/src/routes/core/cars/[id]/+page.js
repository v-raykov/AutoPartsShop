import { error } from '@sveltejs/kit';

let cars = [
    { id: 1, brand: 'Toyota', model: 'Corolla', manufacturer: 'Toyota', year: 2020, parts: ['Brake Pad', 'Oil Filter'] },
];

export function load({ params }) {
    const id = parseInt(params.id);
    const car = cars.find(p => p.id === id);

    if (!car) {
        throw error(404, 'Part not found');
    }

    return {
        car
    };
}

