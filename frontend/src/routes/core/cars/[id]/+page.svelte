<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;

    $: car = data?.car;
    $: brand = data?.brand;
    $: manufacturer = data?.manufacturer;
    $: parts = data?.parts || [];

    $: partButtons = parts.map(part => ({
        label: part.name,
        href: `/core/parts/${part.id}`,
        isButton: true
    }));

    $: fields = [
        { label: 'Brand', value: brand?.name, isButton: true, href: `/core/brands/${brand?.id}` },
        { label: 'Manufacturer', value: manufacturer?.name, isButton: true, href: `/core/manufacturers/${manufacturer?.id}` },
        { label: 'Year', value: car?.year },
        {
            label: 'Parts',
            isButtonGroup: true,
            buttons: partButtons
        }
    ];
</script>

<DetailView item={car} titleProp="model" {fields} />
