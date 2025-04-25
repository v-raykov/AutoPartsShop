<script>
    import DetailView from '$lib/components/DetailView.svelte';
    export let data;
    $: manufacturer = data?.manufacturer;
</script>

<DetailView
        item={manufacturer}
        fields={[
        {label: 'Name', value: `${manufacturer?.name}`},
        {label: 'Address', value: `${manufacturer?.address}`},
        {label: 'Phone Number', value: `${manufacturer?.phoneNumber}`},
        {label: 'Email', value: `${manufacturer?.email}`},
        {label: 'Cars', value: manufacturer?.cars?.join(', ')}
    ]}
/>