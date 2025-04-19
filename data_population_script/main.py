import random
import requests
from faker import Faker

fake = Faker()

BASE_URL = "http://localhost:8080"
NUM_BRANDS = 5
NUM_MANUFACTURERS = 5
NUM_CARS = 10
NUM_PARTS = 15

brand_ids = []
manufacturer_ids = []
car_ids = []

def create_brand():
    data = {"name": fake.company()}
    response = requests.post(f"{BASE_URL}/brand", json=data)
    if response.status_code == 201:
        brand_id = response.json()["id"]
        brand_ids.append(brand_id)
        print(f"Created brand: {brand_id}")
    else:
        print("Failed to create brand", response.text)

def create_manufacturer():
    data = {
        "name": fake.company(),
        "address": fake.address(),
        "phoneNumber": fake.phone_number(),
        "email": fake.company_email()
    }
    response = requests.post(f"{BASE_URL}/manufacturer", json=data)
    if response.status_code == 201:
        manufacturer_id = response.json()["id"]
        manufacturer_ids.append(manufacturer_id)
        print(f"Created manufacturer: {manufacturer_id}")
    else:
        print("Failed to create manufacturer", response.text)

def create_car():
    if not brand_ids or not manufacturer_ids:
        return

    data = {
        "model": fake.word().capitalize(),
        "year": random.randint(2000, 2025),
        "brandId": random.choice(brand_ids),
        "manufacturerId": random.choice(manufacturer_ids)
    }
    response = requests.post(f"{BASE_URL}/car", json=data)
    if response.status_code == 201:
        car_id = response.json()["id"]
        car_ids.append(car_id)
        print(f"Created car: {car_id}")
    else:
        print("Failed to create car", response.text)

def create_part():
    categories = ["ENGINE", "ELECTRICAL", "SUSPENSION", "INTERIOR", "EXTERIOR"]

    # Select random cars to link the part to (between 1 and 3 cars)
    part_car_ids = random.sample(car_ids, k=random.randint(1, min(3, len(car_ids)))) if car_ids else []

    data = {
        "name": fake.word().capitalize(),
        "category": random.choice(categories),
        "priceBuy": random.randint(50, 500),
        "priceSell": random.randint(501, 1000),
        "quantity": random.randint(1, 50),
        "cars": part_car_ids  # Linking cars to part here
    }
    response = requests.post(f"{BASE_URL}/part", json=data)
    if response.status_code == 201:
        part_id = response.json()["id"]
        print(f"Created part: {part_id}")
    else:
        print("Failed to create part", response.text)

def main():
    print("Populating brands...")
    for _ in range(NUM_BRANDS):
        create_brand()

    print("Populating manufacturers...")
    for _ in range(NUM_MANUFACTURERS):
        create_manufacturer()

    print("Populating cars...")
    for _ in range(NUM_CARS):
        create_car()

    print("Populating parts...")
    for _ in range(NUM_PARTS):
        create_part()

if __name__ == "__main__":
    main()
