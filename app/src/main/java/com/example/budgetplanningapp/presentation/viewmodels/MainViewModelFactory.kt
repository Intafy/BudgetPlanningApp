package com.example.budgetplanningapp.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetplanningapp.data.repository.DayItemRepositoryImp
import com.example.budgetplanningapp.data.storage.DbDayItemStorage
import com.example.budgetplanningapp.domain.usecases.LoadListAllItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListMonthItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListWeekItemUseCase
import com.example.budgetplanningapp.domain.usecases.SaveDayItemUseCase

class MainViewModelFactory(context: Context):ViewModelProvider.Factory {

    private val dbDayItemStorage: DbDayItemStorage = DbDayItemStorage(context = context)
    private val dayItemRepositoryImp: DayItemRepositoryImp = DayItemRepositoryImp(dayItemStorage = dbDayItemStorage)
    private val saveDayItemUseCase: SaveDayItemUseCase = SaveDayItemUseCase(dayItemRepository = dayItemRepositoryImp)
    private val loadListAllItemUseCase: LoadListAllItemUseCase = LoadListAllItemUseCase(dayItemRepository = dayItemRepositoryImp)
    private val loadListWeekItemUseCase:LoadListWeekItemUseCase = LoadListWeekItemUseCase(dayItemRepository=dayItemRepositoryImp)
    private val loadListMonthItemUseCase:LoadListMonthItemUseCase = LoadListMonthItemUseCase(dayItemRepository=dayItemRepositoryImp)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(loadListAllItemUseCase,saveDayItemUseCase, loadListWeekItemUseCase = loadListWeekItemUseCase, loadListMonthItemUseCase = loadListMonthItemUseCase) as T
    }
}