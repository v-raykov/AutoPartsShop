<script>
    import './style.css';
    let stats = {
        parts: 120,
        cars: 45,
        brands: 10,
        manufacturers: 5
    };
</script>

<h2>Dashboard Overview</h2>
<div class="card-grid">
    <div class="stat-card">
        <h3>Parts</h3>
        <p class="stat-value">{stats.parts}</p>
        <p class="stat-label">Total Inventory</p>
    </div>
    <div class="stat-card">
        <h3>Vehicles</h3>
        <p class="stat-value">{stats.cars}</p>
        <p class="stat-label">In Catalog</p>
    </div>
    <div class="stat-card">
        <h3>Brands</h3>
        <p class="stat-value">{stats.brands}</p>
        <p class="stat-label">Supported</p>
    </div>
    <div class="stat-card">
        <h3>Manufacturers</h3>
        <p class="stat-value">{stats.manufacturers}</p>
        <p class="stat-label">Partnered</p>
    </div>
</div>
