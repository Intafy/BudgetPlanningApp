package com.example.budgetplanningapp.domain.repository


import com.example.budgetplanningapp.domain.models.DayItem


interface DayItemRepository {

     suspend fun saveDayItemToDb(dayItem: DayItem):Boolean
     suspend fun loadAllIncItemFromDb(): List<DayItem>
     suspend fun loadAllConsItemFromDb(): List<DayItem>
     suspend fun loadWeekIncItemFromDb():List<DayItem>
     suspend fun loadWeekConsItemFromDb():List<DayItem>
     suspend fun loadMonthIncItemFromDb():List<DayItem>
     suspend fun loadMonthConsItemFromDb():List<DayItem>

}