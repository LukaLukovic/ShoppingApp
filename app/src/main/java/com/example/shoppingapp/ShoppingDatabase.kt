package com.example.shoppingapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [ShoppingItem::class],
    version = 1

)
abstract class ShoppingDatabase : RoomDatabase(){


    abstract fun getShoppingDao() : ShoppingItemDao

    companion object{
        @Volatile
        private var instance : ShoppingDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "shopping_item_db.db"
            ).build()
        }
    }

