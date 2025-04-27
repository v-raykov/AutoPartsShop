import axios from 'axios';

const API_URL = 'http://localhost:8080';

const api = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json'
    }
});

export async function fetchAllParts(){
    const res = await api.get(`${API_URL}/part`)
    return await res.data;
}

export async function fetchPartById(id){
    const res = await api.get(`${API_URL}/part/id/${id}`)
    return await res.data;
}

export async function fetchAllCars(){
    const res = await api.get(`${API_URL}/car`)
    return await res.data;
}

export async function fetchCarById(id){
    const res = await api.get(`${API_URL}/car/id/${id}`)
    return await res.data;
}

export async function fetchAllBrands(){
    const res = await api.get(`${API_URL}/brand`)
    return await res.data;
}

export async function fetchBrandById(id){
    const res = await api.get(`${API_URL}/brand/id/${id}`)
    return await res.data;
}

export async function fetchAllManufacturers(){
    const res = await api.get(`${API_URL}/manufacturer`)
    return await res.data;
}

export async function fetchManufacturerById(id){
    const res = await api.get(`${API_URL}/manufacturer/id/${id}`)
    return await res.data;
}


