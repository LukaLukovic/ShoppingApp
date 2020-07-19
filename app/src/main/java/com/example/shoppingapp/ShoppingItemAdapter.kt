package com.example.shoppingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items : List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){


    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.itemView.apply {
            tvName.text = currentShoppingItem.name
            tvAmount.text = "${currentShoppingItem.amount}"

            ivDelete.setOnClickListener {
                viewModel.delete(currentShoppingItem)
            }

            ivMinus.setOnClickListener {
                if(currentShoppingItem.amount > 0){
                    currentShoppingItem.amount--
                }
            }

            ivPlus.setOnClickListener {
                currentShoppingItem.amount++
                viewModel.upsert(currentShoppingItem)
            }

        }
    }
}