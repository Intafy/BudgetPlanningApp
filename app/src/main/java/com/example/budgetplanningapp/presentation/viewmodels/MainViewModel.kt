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
    // Создаем LiveData, которая будет хранить список полученных DayItem и присваиваем ей пустой список
     private var liveDataList = MutableLiveData<ArrayList<DayItem>>()
    // В блоке init запускаем функцию чтения с базы данных всех Item
        init{
        onGetAllFromDb()
        Log.d("MyLog","VM created")
    }
    //Записываем в LiveData значение списка, полученного из БД
    private fun onGetAllFromDb(){
        viewModelScope.launch{
            liveDataList.value=loadListDayItemUseCase.execute() as ArrayList
        }
    }
    //Эта функция возвращает LiveData всем, кто будет подписан на нее
    fun onLoadLiveData():LiveData<ArrayList<DayItem>>{
        return liveDataList
    }
    //Эта функция сохраняет в БД новый Item
    fun onSaveItemToDb(item: DayItem){
        viewModelScope.launch(Dispatchers.IO) {
            //Очищаем значение LiveData, чтобы не дублировались в списке значения
            liveDataList.value!!.clear()

            saveDayItemUseCase.execute(item)
            //Делаем запрос к бд после сохранения
            onGetAllFromDb()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyLog","Vm cleared")
    }
}