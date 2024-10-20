package com.example.budgetplanningapp.data.storage



import androidx.lifecycle.LiveData
import com.example.budgetplanningapp.data.storage.database.ItemStorage

interface DayItemStorage {
     suspend fun save(itemStorage: ItemStorage):Boolean
     suspend fun load(): List<ItemStorage>
}