package com.example.budgetplanningapp.data.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query



@Dao
interface Dao {
    @Insert
    suspend fun insertItem(item:ItemStorage)

    @Query("SELECT *  FROM items WHERE typeItem='Доходы' ORDER BY DATE ASC")
    suspend fun getAllIncItem(): List<ItemStorage>

    @Query("SELECT *  FROM items WHERE typeItem='Расходы' ORDER BY DATE ASC")
    suspend fun getAllConsItem(): List<ItemStorage>

    @Query("SELECT*FROM items WHERE DATE >= date('now','-7 days') AND typeItem='Доходы' ORDER BY DATE ASC")
    suspend fun getWeekIncItem():List<ItemStorage>

    @Query("SELECT*FROM items WHERE DATE >= date('now','-7 days') AND typeItem='Расходы' ORDER BY DATE ASC")
    suspend fun getWeekConsItem():List<ItemStorage>

    @Query("SELECT*FROM items WHERE DATE >= date('now','-1 months') AND typeItem='Доходы' ORDER BY DATE ASC")
    suspend fun getMonthIncItem():List<ItemStorage>

    @Query("SELECT*FROM items WHERE DATE >= date('now','-1 months') AND typeItem='Расходы' ORDER BY DATE ASC")
    suspend fun getMonthConsItem():List<ItemStorage>

}