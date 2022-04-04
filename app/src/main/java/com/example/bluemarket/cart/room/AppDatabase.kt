package com.example.bluemarket.cart.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bluemarket.model.CartProduct


@Database(version = 1, entities = [CartProduct::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun getCartDao(): CartDao

    companion object {
        fun getAppDatabase(context: Context): AppDatabase {
            var appDatabase: AppDatabase? = null
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "db_cart")
                    .allowMainThreadQueries().build()
            }
            return appDatabase
        }
    }

}
