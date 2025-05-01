<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;

    $: brand = data?.brand;
    $: cars = data?.cars || [];

    $: carButtons = cars.map(car => ({
        label: car.model,
        href: `/core/cars/${car.id}`,
        isButton: true
    }));

    $: fields = brand ? [
        { label: 'Name', value: `${brand?.name}` },
        {
            label: 'Cars',
            isButtonGroup: true,
            buttons: carButtons
        }
    ] : [];
</script>

<DetailView item={brand} titleProp="name" {fields} />
