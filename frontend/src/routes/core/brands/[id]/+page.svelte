<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;
    $: brand = data?.brand;
</script>

<DetailView
        item={brand}
        fields={[
        {label: 'Name', value: `$${brand?.priceBuy}`},
        {label: 'Cars', value: brand?.cars?.join(', ')}
    ]}
/>