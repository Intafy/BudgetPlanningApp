package com.example.budgetplanningapp.domain.repository

import com.example.budgetplanningapp.domain.models.DayItem

interface DayItemRepository {

     fun saveDayItemToDb(dayItem: DayItem):Boolean

      fun loadDayItemListFromDb(): ArrayList<DayItem>

}