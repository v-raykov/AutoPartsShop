<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;
    $: car = data?.car;
</script>

<DetailView
        item={car}
        titleProp="model"
        fields={[
        {label: 'Brand', value: car?.brand},
        {label: 'Manufacturer', value: car?.manufacturer},
        {label: 'Year', value: car?.year},
        {label: 'Parts', value: car?.parts?.join(', ')}
    ]}
/>