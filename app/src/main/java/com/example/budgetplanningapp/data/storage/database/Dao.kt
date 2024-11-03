package com.example.budgetplanningapp.data.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query



@Dao
interface Dao {
    @Insert
    suspend fun insertItem(item:ItemStorage)

    @Query("SELECT*FROM items ORDER BY DATE ASC")
    suspend fun getAllItems(): List<ItemStorage>

    @Query("SELECT*FROM items WHERE DATE >= date('now','-7 days') ORDER BY DATE ASC")
    suspend fun getWeekItems():List<ItemStorage>

    @Query("SELECT*FROM items WHERE DATE >= date('now','-1 months') ORDER BY DATE ASC")
    suspend fun getMonthItems():List<ItemStorage>

}