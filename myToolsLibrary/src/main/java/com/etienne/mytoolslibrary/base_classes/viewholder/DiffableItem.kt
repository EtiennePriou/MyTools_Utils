package com.etienne.mytoolslibrary.base_classes.viewholder

import androidx.recyclerview.widget.DiffUtil

/**
 * S'utilise avec DiffableItemCallback. Permet d'utiliser les fonctions de DiffUtil et ListAdapter
 * Dans les modèles plutôt que de définir un callback par adapters.
 */
interface DiffableItem<T : DiffableItem<T>> {
    fun isSameAs(item: T): Boolean
    fun hasSameContentsAs(item: T): Boolean
    fun getChangesPayload(item: T): Any? = null
}

class DiffableItemCallback<T : DiffableItem<T>>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.isSameAs(newItem)
    }
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.hasSameContentsAs(newItem)
    }

    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        return oldItem.getChangesPayload(newItem)
    }
}
