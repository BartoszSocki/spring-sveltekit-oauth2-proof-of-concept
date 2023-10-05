import { sequence } from '@sveltejs/kit/hooks';

import { logoutHandler } from '$lib/Handlers/LogoutHandler';
import { sessionRenewalHandler } from '$lib/Handlers/sessionRenewalHandler';
import { anonymousSessionHandler } from '$lib/Handlers/anonymousSessionHandler';
import { fetchInterceptor } from '$lib/Handlers/fetchInterceptor';

export async function handleFetch(params) {
    return await fetchInterceptor(params)
}

export const handle = sequence(
    logoutHandler,
    sessionRenewalHandler,
    anonymousSessionHandler
)