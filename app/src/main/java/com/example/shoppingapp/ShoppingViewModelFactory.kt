package com.example.shoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoppingViewModelFactory(
    private val repository: ShoppingRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}