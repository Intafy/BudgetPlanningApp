package com.example.budgetplanningapp.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    var liveDataList = MutableLiveData<ArrayList<DayItem>>()

    fun onGetListDataItemFromDb(){
        val dataItem=loadListDayItemUseCase.execute()
        liveDataList.value=dataItem
    }
    fun onSaveItemToDb(item: DayItem){
        saveDayItemUseCase.execute(item)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyLog","Vm cleared")
    }
}