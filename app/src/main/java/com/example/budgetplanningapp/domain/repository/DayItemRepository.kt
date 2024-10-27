package com.example.budgetplanningapp.domain.repository


import com.example.budgetplanningapp.domain.models.DayItem


interface DayItemRepository {

     suspend fun saveDayItemToDb(dayItem: DayItem):Boolean

     suspend fun loadDayItemListFromDb(): List<DayItem>

}