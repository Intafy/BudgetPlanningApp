package com.example.budgetplanningapp.data.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.sql.Timestamp


@Dao
interface Dao {
    @Insert
    suspend fun insertItem(item:ItemStorage)

    @Query("SELECT*FROM items ORDER BY DATE ASC")
    suspend fun getAllItems(): List<ItemStorage>

//    @Query("SELECT*FROM items WHERE DATE() >= DATE('now','weekday','-7 days')")
    @Query("SELECT*FROM items order by date desc")
    suspend fun getWeekItems():List<ItemStorage>
}