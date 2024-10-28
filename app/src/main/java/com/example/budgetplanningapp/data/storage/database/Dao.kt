package com.example.budgetplanningapp.data.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query



@Dao
interface Dao {
    @Insert
    suspend fun insertItem(item:ItemStorage)
    @Query("SELECT*FROM items")
    suspend fun getAllItems(): List<ItemStorage>
//    @Query("SELECT id FROM items WHERE date")
//    suspend fun
}