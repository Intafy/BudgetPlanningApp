package com.example.budgetplanningapp.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetplanningapp.data.repository.DayItemRepositoryImp
import com.example.budgetplanningapp.data.storage.DbDayItemStorage
import com.example.budgetplanningapp.domain.usecases.LoadListAllConsItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListAllIncItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListMonthConsItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListMonthIncItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListWeekConsItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListWeekIncItemUseCase
import com.example.budgetplanningapp.domain.usecases.SaveDayItemUseCase

class MainViewModelFactory(context: Context):ViewModelProvider.Factory {

    private val dbDayItemStorage: DbDayItemStorage = DbDayItemStorage(context = context)
    private val dayItemRepositoryImp: DayItemRepositoryImp = DayItemRepositoryImp(dayItemStorage = dbDayItemStorage)
    private val saveDayItemUseCase: SaveDayItemUseCase = SaveDayItemUseCase(dayItemRepository = dayItemRepositoryImp)

    private val loadListAllIncItemUseCase: LoadListAllIncItemUseCase = LoadListAllIncItemUseCase(dayItemRepository = dayItemRepositoryImp)
    private val loadListAllConsItemUseCase: LoadListAllConsItemUseCase = LoadListAllConsItemUseCase(dayItemRepository = dayItemRepositoryImp)
    private val loadListWeekIncItemUseCase:LoadListWeekIncItemUseCase = LoadListWeekIncItemUseCase(dayItemRepository=dayItemRepositoryImp)
    private val loadListWeekConsItemUseCase:LoadListWeekConsItemUseCase = LoadListWeekConsItemUseCase(dayItemRepository=dayItemRepositoryImp)
    private val loadListMonthIncItemUseCase:LoadListMonthIncItemUseCase = LoadListMonthIncItemUseCase(dayItemRepository=dayItemRepositoryImp)
    private val loadListMonthConsItemUseCase:LoadListMonthConsItemUseCase = LoadListMonthConsItemUseCase(dayItemRepository=dayItemRepositoryImp)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            saveDayItemUseCase = saveDayItemUseCase,
            loadListAllIncItemUseCase = loadListAllIncItemUseCase,
            loadListAllConsItemUseCase = loadListAllConsItemUseCase,
            loadListWeekIncItemUseCase = loadListWeekIncItemUseCase,
            loadListWeekConsItemUseCase = loadListWeekConsItemUseCase,
            loadListMonthIncItemUseCase = loadListMonthIncItemUseCase,
            loadListMonthConsItemUseCase = loadListMonthConsItemUseCase
            ) as T
    }
}