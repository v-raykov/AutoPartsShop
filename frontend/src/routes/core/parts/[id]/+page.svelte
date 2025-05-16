<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;

    $: part = data?.part;
    $: cars = data?.cars || [];

    // Individual buttons per car
    $: carButtons = cars.map(car => ({
        label: car.model,
        href: `/core/cars/${car.id}`,
        isButton: true
    }));

    $: fields = part ? [
        { label: 'Category', value: `${part.category}` },
        { label: 'Buy Price', value: `${part.priceBuy}` },
        { label: 'Sell Price', value: `${part.priceSell}` },
        { label: 'Quantity', value: part.quantity },
        {
            label: 'Compatible Cars',
            isButtonGroup: true,
            buttons: carButtons
        }
    ] : [];
</script>

<DetailView item={part} fields={fields} />
