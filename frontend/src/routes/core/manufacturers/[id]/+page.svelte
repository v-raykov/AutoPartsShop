<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;

    $: manufacturer = data?.manufacturer;
    $: cars = data?.cars || [];

    $: carButtons = cars.map(car => ({
        label: car.model,
        href: `/core/cars/${car.id}`,
        isButton: true
    }));

    $: fields = manufacturer ? [
        { label: 'Name', value: `${manufacturer?.name}` },
        { label: 'Address', value: `${manufacturer?.address}` },
        { label: 'Phone Number', value: `${manufacturer?.phoneNumber}` },
        { label: 'Email', value: `${manufacturer?.email}` },
        {
            label: 'Cars',
            isButtonGroup: true,
            buttons: carButtons
        }
    ] : [];
</script>

<DetailView item={manufacturer} titleProp="name" {fields} />
