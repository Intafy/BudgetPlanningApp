package com.example.budgetplanningapp.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.usecases.LoadListDayItemUseCase
import com.example.budgetplanningapp.domain.usecases.SaveDayItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(
    private val loadListDayItemUseCase: LoadListDayItemUseCase,
    private val saveDayItemUseCase: SaveDayItemUseCase
) :ViewModel() {

    init{
        Log.d("MyLog","VM created")
    }

    //Тут будет хранится информация о доходах/расходах за все дни
    private var liveDataList = MutableLiveData<List<DayItem>>()
    private var tempList:List<DayItem> = listOf()

    fun onGetAllFromDb(): LiveData<List<DayItem>> {
        viewModelScope.launch{
            liveDataList.value=loadListDayItemUseCase.execute()
        }
        return liveDataList
    }

    fun onSaveItemToDb(item: DayItem){
        viewModelScope.launch(Dispatchers.IO) {
            saveDayItemUseCase.execute(item)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyLog","Vm cleared")
    }
}