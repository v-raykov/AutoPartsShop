<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;
    $: brand = data?.brand;
</script>

<DetailView
        item={brand}
        fields={[
        {label: 'Name', value: `${brand?.name}`},
        {label: 'Cars', value: brand?.carIds?.join(', ')}
    ]}
/>