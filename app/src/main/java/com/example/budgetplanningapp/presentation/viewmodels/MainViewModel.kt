package com.example.budgetplanningapp.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.usecases.LoadListAllConsItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListAllIncItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListMonthConsItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListMonthIncItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListWeekConsItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListWeekIncItemUseCase
import com.example.budgetplanningapp.domain.usecases.SaveDayItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val saveDayItemUseCase: SaveDayItemUseCase,
    private val loadListWeekIncItemUseCase: LoadListWeekIncItemUseCase,
    private val loadListWeekConsItemUseCase: LoadListWeekConsItemUseCase,
    private val loadListMonthIncItemUseCase: LoadListMonthIncItemUseCase,
    private val loadListMonthConsItemUseCase: LoadListMonthConsItemUseCase,
    private val loadListAllIncItemUseCase: LoadListAllIncItemUseCase,
    private val loadListAllConsItemUseCase: LoadListAllConsItemUseCase

    ) :ViewModel() {
    // Создаем LiveData, которая будет хранить список полученных DayItem и присваиваем ей пустой список
    private var liveDataAllIncList = MutableLiveData<ArrayList<DayItem>>()
    private var liveDataAllConsList = MutableLiveData<ArrayList<DayItem>>()
    private var liveDataWeekIncList = MutableLiveData<ArrayList<DayItem>>()
    private var liveDataWeekConsList = MutableLiveData<ArrayList<DayItem>>()
    private var liveDataMonthIncList = MutableLiveData<ArrayList<DayItem>>()
    private var liveDataMonthConsList = MutableLiveData<ArrayList<DayItem>>()
    private var liveDataChosenDay = MutableLiveData<String>()

    // В блоке init запускаем функцию чтения с базы данных всех Item
    init {
        liveDataAllIncList.value?.clear()
        onGetAllIncItemFromDb()
        liveDataAllConsList.value?.clear()
        onGetAllConsItemFromDb()
        liveDataWeekIncList.value?.clear()
        onGetWeekIncItemFromDb()
        liveDataWeekConsList.value?.clear()
        onGetWeekConsItemFromDb()
        liveDataMonthIncList.value?.clear()
        onGetMonthIncItemFromDb()
        liveDataMonthConsList.value?.clear()
        onGetMonthConsItemFromDb()
        Log.d("MyLog", "VM created")
    }

    //Записываем в LiveData значение списка, полученного из БД
    private fun onGetAllIncItemFromDb() {
        viewModelScope.launch {
            liveDataAllIncList.value = loadListAllIncItemUseCase.execute() as ArrayList
            Log.d("MyLog", "liveDataAllList: ${liveDataAllIncList.value}")
        }
    }
    private fun onGetAllConsItemFromDb() {
        viewModelScope.launch {
            liveDataAllConsList.value = loadListAllConsItemUseCase.execute() as ArrayList
            Log.d("MyLog", "liveDataAllList: ${liveDataAllConsList.value}")
//        }
        }
    }
    private fun onGetWeekIncItemFromDb(){
        viewModelScope.launch {
            liveDataWeekIncList.value=loadListWeekIncItemUseCase.execute() as ArrayList
            Log.d("MyLog","liveDataWeekList: ${liveDataWeekIncList.value}")
        }
    }
    private fun onGetWeekConsItemFromDb(){
        viewModelScope.launch {
            liveDataWeekConsList.value=loadListWeekConsItemUseCase.execute() as ArrayList
            Log.d("MyLog","liveDataWeekList: ${liveDataWeekConsList.value}")
        }
    }
    private fun onGetMonthIncItemFromDb(){
        viewModelScope.launch {
            liveDataMonthIncList.value=loadListMonthIncItemUseCase.execute() as ArrayList
            Log.d("MyLog","liveDataMonthList: ${liveDataMonthIncList.value}")
        }
    }
    private fun onGetMonthConsItemFromDb(){
        viewModelScope.launch {
            liveDataMonthConsList.value=loadListMonthConsItemUseCase.execute() as ArrayList
            Log.d("MyLog","liveDataMonthList: ${liveDataMonthConsList.value}")
        }
    }

    //Эта функция сохраняет в БД новый Item
    fun onSaveItemToDb(item: DayItem) {
            viewModelScope.launch(Dispatchers.IO) {
                //Очищаем значение LiveData, чтобы не дублировались в списке значения
                liveDataAllIncList.value!!.clear()
                liveDataAllConsList.value!!.clear()
                liveDataWeekIncList.value!!.clear()
                liveDataWeekConsList.value!!.clear()
                liveDataMonthIncList.value!!.clear()
                liveDataMonthConsList.value!!.clear()
                saveDayItemUseCase.execute(item)
                //Делаем запрос к бд после сохранения
                onGetAllIncItemFromDb()
                onGetAllConsItemFromDb()
                onGetMonthIncItemFromDb()
                onGetMonthConsItemFromDb()
                onGetWeekIncItemFromDb()
                onGetWeekConsItemFromDb()
            }
        }
    //Здесь сохраняем дату, полученную с календаря
    fun onSaveChosenDay(chosenDay: String) {
        liveDataChosenDay.value = chosenDay
    }
    //Эти функции возвращают LiveData всем, кто будет подписан на нее
    fun onLoadWeekIncLiveData(): LiveData<ArrayList<DayItem>> {
            return liveDataWeekIncList
        }

    fun onLoadWeekConsLiveData(): LiveData<ArrayList<DayItem>> {
            return liveDataWeekConsList
        }

    fun onLoadMonthIncLiveData(): LiveData<ArrayList<DayItem>> {
            return liveDataMonthIncList
        }

    fun onLoadMonthConsLiveData(): LiveData<ArrayList<DayItem>> {
            return liveDataMonthConsList
        }

    fun onLoadAllIncLiveData(): LiveData<ArrayList<DayItem>> {
            return liveDataAllIncList
        }

    fun onLoadAllConsLivedata(): LiveData<ArrayList<DayItem>> {
            return liveDataAllConsList
 }

    //Возвращает значение дня, выбранного в календаре
    fun onLoadChosenDay(): LiveData<String> {
      return liveDataChosenDay
  }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyLog", "Vm cleared")
    }
}