import { error } from '@sveltejs/kit';

const parts = [
    { id: 1, name: 'Brake Pad', priceBuy: 20, priceSell: 35, quantity: 10, compatibleCars: ['Toyota', 'Honda', 'Ford'] },
    { id: 2, name: 'Oil Filter', priceBuy: 5, priceSell: 10, quantity: 30, compatibleCars: ['Toyota', 'Nissan'] }
];

export function load({ params }) {
    const id = parseInt(params.id);
    const part = parts.find(p => p.id === id);

    if (!part) {
        throw error(404, 'Part not found');
    }

    return {
        part
    };
}

