package com.example.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)


        val shoppingRepository = ShoppingRepository(ShoppingDatabase(this))
        val viewModelProviderFactory = ShoppingViewModelFactory(shoppingRepository)
        val viewmodel : ShoppingViewModel = ViewModelProvider(this,viewModelProviderFactory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(),viewmodel)


        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter


        viewmodel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()


        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,
            object : AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewmodel.upsert(item)
                }
            }).show()
        }
    }
}