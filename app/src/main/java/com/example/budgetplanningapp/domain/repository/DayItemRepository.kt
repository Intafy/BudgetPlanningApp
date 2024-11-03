package com.example.budgetplanningapp.domain.repository


import com.example.budgetplanningapp.domain.models.DayItem


interface DayItemRepository {

     suspend fun saveDayItemToDb(dayItem: DayItem):Boolean
     suspend fun loadAllItemFromDb(): List<DayItem>
     suspend fun loadWeekItemsFromDb():List<DayItem>
     suspend fun loadMonthItemsFromDb():List<DayItem>

}