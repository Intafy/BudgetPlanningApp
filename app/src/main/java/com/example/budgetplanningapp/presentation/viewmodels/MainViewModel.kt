package com.example.budgetplanningapp.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.usecases.LoadListDayItemUseCase
import com.example.budgetplanningapp.domain.usecases.SaveDayItemUseCase



class MainViewModel(
    private val loadListDayItemUseCase: LoadListDayItemUseCase,
    private val saveDayItemUseCase: SaveDayItemUseCase
) :ViewModel() {

    init{
        Log.d("MyLog","VM created")

    }

    //Тут будет хранится информация о доходах/расходах за все дни
    var liveDataList = MutableLiveData<List<DayItem>>()

    fun onGetListDataItemFromDb() {

        liveDataList= loadListDayItemUseCase.execute().asLiveData() as MutableLiveData<List<DayItem>>
        Log.d("MyLog","CoroutineScope is running")

        Log.d("MyLog","liveDataList.value: ${liveDataList.value}")

    }

    fun onSaveItemToDb(item: DayItem){

        saveDayItemUseCase.execute(item)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyLog","Vm cleared")
    }
}