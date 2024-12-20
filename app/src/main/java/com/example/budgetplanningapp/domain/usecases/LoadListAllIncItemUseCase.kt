package com.example.budgetplanningapp.domain.usecases

import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.repository.DayItemRepository

class LoadListAllIncItemUseCase(private val dayItemRepository: DayItemRepository){
    suspend fun execute(): List<DayItem> {

        return dayItemRepository.loadAllIncItemFromDb()
    }

}