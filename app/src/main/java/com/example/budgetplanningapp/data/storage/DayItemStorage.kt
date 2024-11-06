package com.example.budgetplanningapp.data.storage

import com.example.budgetplanningapp.data.storage.database.ItemStorage

interface DayItemStorage {
     suspend fun save(itemStorage: ItemStorage):Boolean
     suspend fun loadAllIncItem(): List<ItemStorage>
     suspend fun loadAllConsItem(): List<ItemStorage>
     suspend fun loadWeekIncItemFromDb():List<ItemStorage>
     suspend fun loadWeekConsItemFromDb():List<ItemStorage>
     suspend fun loadMonthIncItemFromDb():List<ItemStorage>
     suspend fun loadMonthConsItemFromDb():List<ItemStorage>
}