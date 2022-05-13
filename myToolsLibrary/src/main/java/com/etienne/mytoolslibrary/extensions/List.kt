package com.etienne.mytoolslibrary.extensions

fun <T> MutableList<T>.replaceAt(index: Int, item: T) {
    removeAt(index)
    add(index, item)
}

fun <E> MutableList<E>.replaceLast(item: E) {
    replaceAt(size-1, item)
}

fun <E> List<E>.replaceAt(index: Int, item: E): List<E> {
    val list = toMutableList()
    list.removeAt(index)
    list.add(index, item)
    return list
}

fun <E> List<E>.replaceOrAddList(items: List<E>, condition: (E, E) -> Boolean): MutableList<E> {
    val list = this.toMutableList()
    items.forEach { item ->
        val index = list.indexOfLast {itemTc -> condition(itemTc, item) }
        if (index >= 0){
            list.replaceAt(index, item)
        }else{
            list.add(item)
        }
    }
    return list
}

fun <E> List<E>.toApiString(): String{
    var str = ""
    this.forEachIndexed { index, e ->
        if (e is String) str += "\""
        str += e.toString()
        if (e is String) str += "\""
        str += if (index == this.size -1) "" else ","
    }
    return str
}

fun <E> List<E>.toGetString(): String{
    var str = this.toString()
        .replace(" ","")
        .replace("[","")
        .replace("]", "")
        .replace(",,",",")
    if (str.isNotEmpty()){
        if (str[str.length -1] == (",")[0]){
            str = str.removeRange(str.length -1, str.length)
        }
    }
    return str
}
