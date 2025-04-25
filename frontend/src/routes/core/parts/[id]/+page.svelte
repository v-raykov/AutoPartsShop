<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;
    $: part = data?.part;
</script>

<DetailView
        item={part}
        fields={[
        {label: 'Buy Price', value: `$${part?.priceBuy}`},
        {label: 'Sell Price', value: `$${part?.priceSell}`},
        {label: 'Quantity', value: part?.quantity},
        {label: 'Compatible Cars', value: part?.compatibleCars?.join(', ')}
    ]}
/>