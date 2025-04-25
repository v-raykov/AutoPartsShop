import { error } from '@sveltejs/kit';

let manufacturers = [
    { id: 1, name: 'Krasimir', address: '123 Main St, Sofia', phoneNumber: '123-456-7890', email: 'dimitur@example.com', cars: ['Toyota', 'Honda', 'Ford'] },
    { id: 2, name: 'Jelezov', address: '456 Elm St, Plovdiv', phoneNumber: '987-654-3210', email: 'domozetov@example.com', cars: ['Toyota', 'Nissan'] }
];

export function load({ params }) {
    const id = parseInt(params.id);
    const manufacturer = manufacturers.find(p => p.id === id);

    if (!manufacturer) {
        throw error(404, 'Part not found');
    }

    return {
        manufacturer
    };
}
