package com.etienne.mytoolslibrary.base_classes.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BindingViewHolder<T : ViewBinding>(val bind: T): RecyclerView.ViewHolder(bind.root)
