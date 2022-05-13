package com.etienne.mytoolslibrary.extensions

inline fun <E> MutableList<E>.insertOrUpdate(element: E, predicate: (E) -> Boolean) {
    val index = indexOfFirst(predicate)
    if (index == -1) {
        add(element)
    } else {
        set(index, element)
    }
}

@Suppress("UNCHECKED_CAST")
fun <E> MutableList<E?>.removeAllNull(): List<E> {
    this.removeAll { it == null }
    return this as MutableList<E>
}

fun <E> MutableList<E>.addOrReplaceAt(pos:Int, item:E){
    if (this.size > pos){
        this.replaceAt(pos, item)
    }else{
        this.add(pos, item)
    }
}