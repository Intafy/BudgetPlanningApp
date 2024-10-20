package com.example.budgetplanningapp.domain.usecases

import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.repository.DayItemRepository

class SaveDayItemUseCase(private val dayItemRepository: DayItemRepository) {

    suspend fun  execute(dayItem: DayItem):Boolean {
        val result:Boolean = dayItemRepository.saveDayItemToDb(dayItem)
        return result
    }
}