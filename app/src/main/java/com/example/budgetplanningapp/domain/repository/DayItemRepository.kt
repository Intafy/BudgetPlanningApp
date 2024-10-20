package com.example.budgetplanningapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.budgetplanningapp.domain.models.DayItem
import kotlinx.coroutines.flow.Flow

interface DayItemRepository {

     suspend fun saveDayItemToDb(dayItem: DayItem):Boolean

     fun loadDayItemListFromDb(): LiveData<List<DayItem>>

}