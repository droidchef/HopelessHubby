package com.raywenderlich.hopelesshubby

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_shopping_lists.*
import kotlinx.android.synthetic.main.content_shopping_lists.*

class ShoppingListActivity : AppCompatActivity() {

    /**
     * Layout manager for the Recycler View.
     */
    private lateinit var layoutManager: LinearLayoutManager
    /**
     * Adapter for the Shopping Items Recycler View.
     */
    private lateinit var shoppingListAdapter: ShoppingListAdapter

    private lateinit var viewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_lists)
        setSupportActionBar(toolbar)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager as RecyclerView.LayoutManager?

        viewModel = ViewModelProviders.of(this, SavedStateViewModelFactory(this)).get(ShoppingListViewModel::class.java)

        btAddToList.setOnClickListener {
            val itemName = etItemName.text.toString()
            if (itemName.isNotEmpty()) {
                val position = viewModel.getItems().value?.size
                viewModel.addItemToShoppingList(itemName)
                shoppingListAdapter.notifyItemInserted(position ?: 0)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        loadShoppingLists()
    }

    /**
     * Loads the items in the shopping list and displays them in the recycler view.
     */
    private fun loadShoppingLists() {
        viewModel.loadShoppingList()

        viewModel.getItems().observe(this, Observer<ArrayList<String>> {
            shoppingListAdapter = ShoppingListAdapter(viewModel.getItems().value ?: arrayListOf())
            recyclerView.adapter = shoppingListAdapter
            shoppingListAdapter.notifyDataSetChanged()
        })
    }


}
