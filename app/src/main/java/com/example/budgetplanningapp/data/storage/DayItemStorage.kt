package com.example.budgetplanningapp.data.storage

import com.example.budgetplanningapp.data.storage.database.ItemStorage

interface DayItemStorage {
    fun save(itemStorage: ItemStorage):Boolean
    fun load():List<ItemStorage>
}