package com.example.budgetplanningapp.data.storage

interface DayItemStorage {
    fun save(itemStorage:ItemStorage):Boolean
    fun load():List<ItemStorage>
}