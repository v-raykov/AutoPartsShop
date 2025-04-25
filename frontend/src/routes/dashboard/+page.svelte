<script>
    import './style.css';

    let stats = {
        parts: 120,
        cars: 45,
        brands: 10,
        manufacturers: 5
    };
</script>


<h2>Dashboard</h2>
<div class="grid">
    <div class="card">🔩 Parts: {stats.parts}</div>
    <div class="card">🚗 Cars: {stats.cars}</div>
    <div class="card">🏷️ Brands: {stats.brands}</div>
    <div class="card">🏭 Manufacturers: {stats.manufacturers}</div>
</div>