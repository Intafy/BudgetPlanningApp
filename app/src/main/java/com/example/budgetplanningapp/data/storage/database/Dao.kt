package com.example.budgetplanningapp.data.storage.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query



@Dao
interface Dao {
    @Insert
    suspend fun insertItem(item:ItemStorage)
    @Query("SELECT*FROM items ORDER BY id ASC")
    fun getAllItems(): LiveData<List<ItemStorage>>
}