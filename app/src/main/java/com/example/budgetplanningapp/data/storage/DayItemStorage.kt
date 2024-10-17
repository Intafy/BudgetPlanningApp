package com.example.budgetplanningapp.data.storage


import com.example.budgetplanningapp.data.storage.database.ItemStorage
import kotlinx.coroutines.flow.Flow

interface DayItemStorage {
     suspend fun save(itemStorage: ItemStorage):Boolean
     suspend fun load(): Flow<ItemStorage>
}