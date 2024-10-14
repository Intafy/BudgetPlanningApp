package com.example.budgetplanningapp.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budgetplanningapp.domain.models.DayItem

class MainViewModel:ViewModel() {
    init{
        Log.d("MyLog","VM created")
    }
    //Тут создается список, хранящий доход и расход в разные дни, иммитируя базу данных
     private var listItem= arrayListOf<DayItem>(
        DayItem("21.03.20",5.0,4.0,1.0),
        DayItem("22.03.20",7.0,3.0,4.0),
        DayItem("23.03.20",8.0,9.0,-1.0),
        DayItem("24.03.20",2.0,3.0,-1.0),
        DayItem("25.03.20",6.0,1.0,5.0)
    )

    //Тут будет хранится информация о доходах/расходах текущего дня
    var liveDateCurrent = MutableLiveData<DayItem>()
    //Тут будет хранится информация о доходах/расходах за все дни
    var liveDateList = MutableLiveData<List<DayItem>>()

//    fun onGetListDataItem():ArrayList<DayItem>{
//        return listItem
//    }
//    fun onAddItemToList(item: DayItem){
//        listItem.add(item)
//    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyLog","Vm cleared")
    }
}