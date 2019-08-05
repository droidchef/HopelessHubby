package com.raywenderlich.hopelesshubby

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class ShoppingListViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val KEY = "Saved_Shopping_List"

    fun getItems(): LiveData<ArrayList<String>> {
        return savedStateHandle.getLiveData(KEY)
    }

    fun loadShoppingList() {

        if (getItems().value != null) {
            if (getItems().value!!.isEmpty()) {
                getItems().value!!.addAll(generateItems())
                savedStateHandle.set(KEY, getItems().value)
            }
        } else {
            savedStateHandle.set(KEY, arrayListOf<String>())
            loadShoppingList()
        }

    }

    fun addItemToShoppingList(itemName: String) {
        getItems().value!!.add(itemName)
        savedStateHandle.set(KEY, getItems().value)
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