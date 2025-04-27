<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;
    $: car = data?.car;
</script>

<DetailView
        item={car}
        titleProp="model"
        fields={[
        {label: 'Brand', value: car?.brandId},
        {label: 'Manufacturer', value: car?.manufacturerId},
        {label: 'Year', value: car?.year},
        {label: 'Parts', value: car?.partIds?.join(', ')}
    ]}
/>