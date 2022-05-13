package com.etienne.mytoolslibrary.extensions

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.etienne.mytoolslibrary.base_classes.viewholder.BindingViewHolder

/**
 * Traite le viewHolder comme un DatabindingViewHolder et cast son binding au type souhaité.
 */
inline fun <reified T : ViewBinding> RecyclerView.ViewHolder.asBinding(): T {
    return (this as BindingViewHolder<*>).bind as T
}