package com.digitalkoi.core.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

fun <T, V> CoroutineScope.asyncAll(
    list: List<T>,
    block: suspend (T) -> V
): List<Deferred<V>> =
    list.map {
        async { block.invoke(it) }
    }