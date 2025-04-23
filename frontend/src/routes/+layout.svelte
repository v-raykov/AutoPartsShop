<script>
  import './style.css';
</script>

<header>
  <span>Auto Parts Shop</span>
  <div class="user-controls">
    <!-- Would add user controls/avatar here -->
  </div>
</header>

<div class="container">
  <nav class="sidebar">
    <a href="/dashboard" class="nav-item">Dashboard</a>
    <a href="/core/parts" class="nav-item">Parts Inventory</a>
    <a href="/core/cars" class="nav-item">Vehicles</a>
  </nav>

  <main class="main">
    <slot />
  </main>
</div>
