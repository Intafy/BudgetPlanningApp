package com.example.budgetplanningapp.domain.usecases

import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.repository.DayItemRepository
import kotlinx.coroutines.flow.Flow

class LoadListDayItemUseCase(private val dayItemRepository: DayItemRepository) {

        fun execute(): Flow<List<DayItem>> {
        return dayItemRepository.loadDayItemListFromDb()
    }

}