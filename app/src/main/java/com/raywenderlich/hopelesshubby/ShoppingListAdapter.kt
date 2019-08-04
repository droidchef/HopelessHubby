package com.raywenderlich.hopelesshubby

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ShoppingListAdapter(val shoppingListItems: ArrayList<String>) :
    RecyclerView.Adapter<ShoppingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ShoppingListViewHolder(view)
    }

    override fun getItemCount() = shoppingListItems.size

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(shoppingListItems[position])
    }
}


class ShoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(shoppingListItem: String) {
        itemView.text.text = shoppingListItem
    }


}