package com.example.budgetplanningapp.domain.repository

import com.example.budgetplanningapp.domain.models.DayItem
import kotlinx.coroutines.flow.Flow

interface DayItemRepository {

     fun saveDayItemToDb(dayItem: DayItem):Boolean

     fun loadDayItemListFromDb(): Flow<List<DayItem>>

}