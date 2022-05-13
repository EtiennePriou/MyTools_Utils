package com.etienne.mytoolslibrary.extensions

import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.EditText
import androidx.core.text.isDigitsOnly

fun EditText.addAfterTextChangedListener(listener: (s: Editable) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            s?.let(listener)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun EditText.setTextWithSelection(s: CharSequence?) {
    setText(s)
    setSelection(text.length)
}

fun EditText.addMaxTextCharsFilter(max:Int){
    val filterArray: MutableList<InputFilter> = this.filters.toMutableList()
    filterArray.add(InputFilter.LengthFilter(max))
    this.filters = filterArray.toTypedArray()
}

fun EditText.addEmojiFilter(){
    val filterArray: MutableList<InputFilter> = this.filters.toMutableList()
    filterArray.add(emojiFilter())
    this.filters = filterArray.toTypedArray()
}

fun EditText.addPrefixFilter(prefix: String) {
    val filterArray : MutableList<InputFilter> = this.filters.toMutableList()
    val prefixFilter = InputFilter { charSequence, _, _, dest, destStart, destEnd ->
        when {
            dest.isEmpty() && charSequence.isEmpty() -> prefix
            dest.isEmpty() && charSequence.contains(prefix) -> charSequence
            destStart + 1 > prefix.length && destEnd + 1 > prefix.length -> charSequence
            destStart < destEnd -> dest[destStart].toString()
            else -> ""
        }
    }
    this.accessibilityDelegate = object : View.AccessibilityDelegate() {
        override fun sendAccessibilityEvent(host: View?, eventType: Int) {
            super.sendAccessibilityEvent(host, eventType)
            if (host is EditText) {
                if (host.text?.length != prefix.length)
                    return
                else {
                    if (eventType == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED) {
                        if (selectionStart < prefix.length || selectionEnd < prefix.length)
                            try {
                                setSelection(prefix.length)
                            }catch (e:Exception){
                                Log.e("addPrefixFilter", "TYPE_VIEW_TEXT_SELECTION_CHANGED", e)
                                e.printStackTrace()
                            }
                    }
                }
            }
        }
    }
    filterArray.add(prefixFilter)
    this.filters = filterArray.toTypedArray()
}

fun emojiFilter(): InputFilter {
    return InputFilter { source, start, end, _, _, _ ->
        for (i in start until end) {
            val type = Character.getType(source[i])
            if (type == Character.SURROGATE.toInt() || type == Character.OTHER_SYMBOL.toInt()) {
                return@InputFilter ""
            }
        }
        null
    }
}

fun EditText.addDigitFilter(){
    val filterArray: MutableList<InputFilter> = this.filters.toMutableList()
    val filter = InputFilter{ source, _, _, _, _, _ ->
        if (source.isDigitsOnly()) source
        else ""
    }
    filterArray.add(filter)
    this.filters = filterArray.toTypedArray()
}
fun EditText.addCreditCardFilter(){
    this.addTextChangedListener(object :TextWatcher{
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =Unit
        override fun afterTextChanged(s: Editable) {
            if (s.isBlank()) {
                s.clear()
                return
            }
            // Handle first char outside the loop
            if (!s[0].isDigit()) {
                s.replace(0, 1, "")
            }
            s.forEachIndexed { i, character ->
                when {
                    (i + 1) % 5 == 0 -> {
                        // Every fifth character should be a space
                        if (character != ' ') {
                            s.insert(i, " ")
                        }
                    }
                    else -> {
                        // Every other character should be a number
                        if (!character.isDigit()) {
                            s.replace(i, i + 1, "")
                        }
                    }
                }
            }
        }
    })
}
fun EditText.addCreditCardDateFilter(){
    this.addTextChangedListener(object :TextWatcher{
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =Unit
        override fun afterTextChanged(s: Editable) {
            if (s.isBlank()) {
                s.clear()
                return
            }
            // Handle first char outside the loop
            if (!s[0].isDigit()) {
                s.replace(0, 1, "")
            }
            s.forEachIndexed { i, character ->
                when {
                    (i + 1) % 3 == 0 -> {
                        // Every fifth character should be a space
                        if (character != '/') {
                            s.insert(i, "/")
                        }
                    }
                    else -> {
                        // Every other character should be a number
                        if (!character.isDigit()) {
                            s.replace(i, i + 1, "")
                        }
                    }
                }
            }
        }
    })
}

fun EditText.addAllCapFilter(){
    val filterArray: MutableList<InputFilter> = this.filters.toMutableList()
    val filter = InputFilter.AllCaps()
    filterArray.add(filter)
    this.filters = filterArray.toTypedArray()
}
fun EditText.addIbanFilter(){
    this.addAllCapFilter()
    this.addTextChangedListener(object :TextWatcher{
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =Unit
        override fun afterTextChanged(s: Editable) {
            if (s.isBlank()) {
                s.clear()
                return
            }
            // Handle first char outside the loop
            if (!s[0].isLetterOrDigit()) {
                s.replace(0, 1, "")
            }
            s.forEachIndexed { i, character ->
                when {
                    (i + 1) % 5 == 0 -> {
                        // Every four character should be a space
                        if (character != ' ') {
                            s.insert(i, " ")
                        }
                    }
                    else -> {
                        // Every other character should be a number or digit
                        if (!character.isLetterOrDigit()) {
                            s.replace(i, i + 1, "")
                        }
                    }
                }
            }
        }
    })
}
fun EditText.addCurrencyFilter(allowNegative: Boolean = false, digit:Int = 2, allowDigit:Boolean = true){
    val keyListener = DigitsKeyListener.getInstance("0123456789${if (allowDigit)",." else ""}${if (allowNegative)"-" else ""}")
    this.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
    this.keyListener = keyListener
    val filterArray: MutableList<InputFilter> = this.filters.toMutableList()
    filterArray.add(currencyFilter(digit, allowDigit))
    this.filters = filterArray.toTypedArray()
    if (allowDigit){
        this.setOnFocusChangeListener { _, _ ->
            if (this.text.isNullOrBlank()) return@setOnFocusChangeListener
            val unitPrice = try {
                val tmp = this.text.toString().replace(",", ".")
                tmp.toDouble()
            }catch (e: Exception){
                0.0
            }
            if (this.text?.toString() != String.format("%.${digit}f", unitPrice)){
                this.text = (String.format("%.${digit}f", unitPrice)).toEditable()
            }
        }
    }
}

fun currencyFilter(digit:Int, allowDigit: Boolean) = InputFilter { source, _, _, dest, destinationStart, _ ->
    when{
        source == "-" && (destinationStart != 0 || dest.contains("-")) -> ""
        dest.contains(",") || dest.contains(".") -> {
            if (source == "," || source == ".") ""
            else {
                val i = dest.indexOfFirst { (it == (",")[0] || it == (".")[0]) }
                if (destinationStart> i && dest.length - (i+1) >= digit) {
                    ""
                }
                else {
                    source
                }
            }
        }
        !source.isDigitsOnly() && !allowDigit -> source.filter { it.isDigit() }
        else -> source
    }
}
