import axios from 'axios';

const isServer = typeof window === 'undefined';

const backendUrl = import.meta.env.VITE_BACKEND_URL || 'http://localhost:8080';

const api = axios.create({
    baseURL: isServer
        ? backendUrl
        : '/api',
    headers: {
        'Content-Type': 'application/json',
    },
});

export async function fetchAllBrands() {
    const res = await api.get(`/brand`)
    return await res.data;
}

export async function fetchBrandById(id) {
    const res = await api.get(`/brand/id/${id}`)
    return await res.data;
}

export async function deleteBrand(id) {
    const res = await api.delete(`/brand/id/${id}`)
    return await res.data;
}

export async function addBrand(name, carIds) {
    const res = await api.post(`/brand`, { name, carIds })
    return await res.data;
}

export async function fetchAllCars() {
    const res = await api.get(`/car`)
    return await res.data;
}

export async function fetchCarById(id) {
    const res = await api.get(`/car/id/${id}`)
    return await res.data;
}

export async function fetchCarsByBrandId(id) {
    const res = await api.get(`/car/brand/${id}`)
    return await res.data;
}

export async function fetchCarsByManufacturerId(id) {
    const res = await api.get(`/car/manufacturer/${id}`)
    return await res.data;
}

export async function fetchCarsByPartId(id) {
    const res = await api.get(`/car/part/${id}`)
    return await res.data;
}

export async function addCar(car) {
    const res = await api.post('/car', car);
    return res.data;
}

export async function deleteCar(id) {
    const res = await api.delete(`/car/id/${id}`)
    return await res.data;
}

export async function fetchAllManufacturers() {
    const res = await api.get(`/manufacturer`)
    return await res.data;
}

export async function fetchManufacturerById(id) {
    const res = await api.get(`/manufacturer/id/${id}`)
    return await res.data;
}

export async function addManufacturer(manufacturer) {
    const res = await api.post('/manufacturer', manufacturer);
    return res.data;
}

export async function deleteManufacturer(id) {
    const res = await api.delete(`/manufacturer/id/${id}`)
    return await res.data;
}

export async function fetchAllParts() {
    const res = await api.get(`/part`)
    return await res.data;
}

export async function fetchPartById(id) {
    const res = await api.get(`/part/id/${id}`)
    return await res.data;
}

export async function fetchPartsByCarId(id) {
    const res = await api.get(`/part/car/${id}`)
    return await res.data;
}

export async function addPart(part) {
    const res = await api.post('/part', part);
    return res.data;
}

export async function fetchAllCategories() {
    const res = await api.get(`/part/category`)
    return await res.data;
}

export async function deletePart(id) {
    const res = await api.delete(`/part/id/${id}`)
    return await res.data;
}