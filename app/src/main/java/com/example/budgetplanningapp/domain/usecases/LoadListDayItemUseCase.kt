package com.example.budgetplanningapp.domain.usecases

import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.repository.DayItemRepository

class LoadListDayItemUseCase(private val dayItemRepository: DayItemRepository) {

    fun execute(): ArrayList<DayItem> {
        return dayItemRepository.loadDayItemListFromDb()
    }

}