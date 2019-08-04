package com.raywenderlich.hopelesshubby

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ShoppingListViewModel() : ViewModel() {

    /**
     * An array list of ShoppingListItem.
     */
    val items = MutableLiveData<ArrayList<String>>()


    fun loadShoppingList() {

        if (items.value.isNullOrEmpty()) {
            items.value = generateItems()

        }
    }

    fun addItemToShoppingList(itemName: String) {
        items.value?.add(itemName)
    }

    /**
     * Generates a list of items for the shopping cart!
     */
    private fun generateItems(): ArrayList<String> {

        val list = ArrayList<String>()

        list.add("Milk")
        list.add("Eggs")
        list.add("Oranges")

        return list
    }


}