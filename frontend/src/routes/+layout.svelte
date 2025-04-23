<script>
    import './style.css'
</script>

<header>Auto Parts Shop</header>
<div class="container">
    <div class="sidebar">
        <a href="/dashboard" class="nav-item">🏠 Dashboard</a>
        <a href="/core/parts" class="nav-item">🔩 Parts</a>
        <a href="/core/cars" class="nav-item">🚗 Cars</a>
    </div>
    <div class="main">
        <slot />
    </div>
</div>
