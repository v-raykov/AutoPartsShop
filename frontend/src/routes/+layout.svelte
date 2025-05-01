<script>
  import '../app.css';
</script>

<header>
  <span>Auto Parts Shop</span>
  <div class="user-controls">
  </div>
</header>

<div class="container">
  <nav class="sidebar">
    <a href="/dashboard" class="nav-item">Dashboard</a>
    <a href="/core/parts" class="nav-item">Parts Inventory</a>
    <a href="/core/cars" class="nav-item">Vehicles</a>
    <a href="/core/brands" class="nav-item">Brands</a>
    <a href="/core/manufacturers" class="nav-item">Manufacturers</a>
  </nav>

  <main class="main">
    <slot />
  </main>
</div>
