package com.example.budgetplanningapp.data.storage.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    fun insertItem(item:ItemStorage)
    @Query("SELECT*FROM items")
    fun getAllItems(): Flow<ItemStorage>
}