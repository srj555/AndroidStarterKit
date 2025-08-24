package com.acme.core.utils

import kotlinx.coroutines.delay

suspend inline fun <T> retry(
    times: Int = 3,
    initialDelayMs: Long = 200,
    maxDelayMs: Long = 2_000,
    factor: Double = 2.0,
    block: () -> T
): T {
    var currentDelay = initialDelayMs
    repeat(times - 1) {
        try { return block() } catch (_: Throwable) { }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelayMs)
    }
    return block()
}