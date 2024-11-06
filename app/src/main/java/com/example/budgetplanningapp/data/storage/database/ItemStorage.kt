package com.example.budgetplanningapp.data.storage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemStorage(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name="date")
    val date: String,
    @ColumnInfo(name="income/consumption")
    val incomeConsumption: Double,
    @ColumnInfo(name="typeItem")
    val typeItem: String
)


