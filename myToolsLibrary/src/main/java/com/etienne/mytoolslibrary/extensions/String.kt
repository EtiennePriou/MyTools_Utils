package com.etienne.mytoolslibrary.extensions

import android.text.Editable
import java.util.*
import java.util.regex.Pattern

const val regexMail = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
const val regexPhone = "^(06|07)([.]| |-|)([0-9]{2}([.]| |-|)){3}[0-9]{2}$"

fun String.getEscapedString(): String {
    return this.replace("\\\\n".toRegex(), "\n")
            .replace("\\\\r".toRegex(), "\r")
}

fun String.isMail() = Pattern.matches(regexMail, this)
fun String.isNotMail() = !this.isMail()

fun String.isPhone() = Pattern.matches(regexPhone, this)
fun String.isNotPhone() = !this.isPhone()

fun String.toEditable(): Editable?{
    return Editable.Factory.getInstance().newEditable(this)
}

fun String.notEmpty(doThis:() -> Unit){
    if (this.isEmpty()) return
    else doThis()
}

fun String?.notNullOrEmpty(doThis:(String) -> Unit, elseDo: () -> Unit){
    if (this.isNullOrEmpty()) elseDo()
    else doThis(this)
}

fun getRandomString(length: Int) : String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
    return (1..length)
            .map { charset.random() }
            .joinToString("")
}

fun String.capitalize(): String{
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

fun String.contains(vararg words: String, ignoreCase:Boolean = true): Boolean{
    var contained = false
    words.forEach containLoop@{
        if (this.contains(it, ignoreCase)){
            contained = true
            return@containLoop
        }
    }
    return contained
}
