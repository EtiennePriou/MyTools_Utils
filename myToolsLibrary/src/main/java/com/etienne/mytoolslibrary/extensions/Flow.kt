package com.etienne.mytoolslibrary.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * Created by etien on 07/01/2022.
 */

fun <T> Flow<T>.onError(onError: ((Throwable) -> Unit)?): Flow<T> = catch { e -> onError?.let { it(e) } }