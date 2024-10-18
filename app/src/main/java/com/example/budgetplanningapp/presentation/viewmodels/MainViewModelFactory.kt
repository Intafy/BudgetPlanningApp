package com.example.budgetplanningapp.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetplanningapp.data.repository.DayItemRepositoryImp
import com.example.budgetplanningapp.data.storage.DbDayItemStorage
import com.example.budgetplanningapp.domain.usecases.LoadListDayItemUseCase
import com.example.budgetplanningapp.domain.usecases.SaveDayItemUseCase

class MainViewModelFactory(context: Context):ViewModelProvider.Factory {

    private val dbDayItemStorage: DbDayItemStorage = DbDayItemStorage(context = context)
    private val dayItemRepositoryImp: DayItemRepositoryImp = DayItemRepositoryImp(dayItemStorage = dbDayItemStorage)
    private val loadListDayItemUseCase: LoadListDayItemUseCase = LoadListDayItemUseCase(dayItemRepository = dayItemRepositoryImp)
    private val saveDayItemUseCase: SaveDayItemUseCase = SaveDayItemUseCase(dayItemRepository = dayItemRepositoryImp)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(loadListDayItemUseCase,saveDayItemUseCase) as T
    }
}