package com.example.shoppingapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context: Context, var addDialogListener : AddDialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)


        tvAdd.setOnClickListener {
            val name = etItemName.text.toString()
            val amount = etItemAmount.text.toString()


            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context, "Please fill all of the input fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }


        tvCancel.setOnClickListener{

            cancel()
        }
    }

}