<script lang="ts">
    import { onMount } from 'svelte';
    import type { Member } from '$lib/types/Member';

    let member: Member | null = null;

    onMount(async () => {
        const response = await fetch('/api/member/1');
        if (response.ok) {
            member = await response.json();
        }
    });
</script>

<h1>Member Details</h1>
{#if member}
    <p>Name: {member.name}</p>
    <p>Age: {member.age}</p>
    <p>Location: {member.location?.address || 'N/A'}</p>
{:else}
    <p>Loading...</p>
{/if}
