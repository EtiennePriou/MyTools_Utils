package com.etienne.mytoolslibrary.extensions

inline fun <T, U> Iterable<T>.filterMap(transform: (T) -> U?): MutableList<U> {
    val newList = mutableListOf<U>()
    this.forEach {
        val newItem = transform(it as T)
        if (newItem != null) newList.add(newItem)
    }
    return newList
}
