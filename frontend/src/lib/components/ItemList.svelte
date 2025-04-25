<script>
    export let items = [];
    export let itemType = '';

    function getItemLink(item) {
        return `/${itemType.toLowerCase()}s/${item.id}`;
    }
</script>

<h1>{itemType}s</h1>
<ul>
    {#each items as item}
        <li><a href={getItemLink(item)}>{item.name}</a></li>
    {/each}
</ul>