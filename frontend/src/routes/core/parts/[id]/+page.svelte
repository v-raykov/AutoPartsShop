<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;
    $: part = data?.part;

    $: fields = part ? [
        {label: 'Buy Price', value: `${part.priceBuy}`},
        {label: 'Sell Price', value: `${part.priceSell}`},
        {label: 'Quantity', value: part.quantity},
        {label: 'Compatible Cars', value: part.carIds?.join(', ')}
    ] : [];
</script>

<DetailView item={part} fields={fields} />
