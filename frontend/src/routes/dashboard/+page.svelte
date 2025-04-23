<script>
    let stats = {
        parts: 120,
        cars: 45,
        brands: 10,
        manufacturers: 5
    };
</script>

<style>
    .grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 1.5rem;
        margin-top: 1rem;
    }
    .card {
        background: white;
        padding: 1.5rem;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        text-align: center;
        font-size: 1.2rem;
    }
</style>

<h2>Dashboard</h2>
<div class="grid">
    <div class="card">🔩 Parts: {stats.parts}</div>
    <div class="card">🚗 Cars: {stats.cars}</div>
    <div class="card">🏷️ Brands: {stats.brands}</div>
    <div class="card">🏭 Manufacturers: {stats.manufacturers}</div>
</div>
