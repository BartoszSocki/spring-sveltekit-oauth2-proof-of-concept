import { sequence } from '@sveltejs/kit/hooks';

import { logoutHandler } from '$lib/server/Handlers/LogoutHandler';
import { sessionRenewalHandler } from '$lib/server/Handlers/sessionRenewalHandler';
import { anonymousSessionHandler } from '$lib/server/Handlers/anonymousSessionHandler';
import { fetchInterceptor } from '$lib/server/Handlers/fetchInterceptor';

export async function handleFetch(params) {
    return await fetchInterceptor(params)
}

export const handle = sequence(
    logoutHandler,
    sessionRenewalHandler,
    anonymousSessionHandler
)