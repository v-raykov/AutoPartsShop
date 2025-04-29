<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;
    $: car = data?.car;
    $: brand = data?.brand;
    $: manufacturer = data?.manufacturer;
</script>

<DetailView
        item={car}
        titleProp="model"
        fields={[
        { label: 'Brand', value: brand?.name, isButton: true, href: `/core/brands/${brand?.id}` },
        { label: 'Manufacturer', value: manufacturer?.name, isButton: true, href: `/core/manufacturers/${manufacturer?.id}` },
        { label: 'Year', value: car?.year },
        { label: 'Parts', value: car?.partIds?.join(', ') }
    ]}
/>
