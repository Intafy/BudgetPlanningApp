package com.example.budgetplanningapp.presentation.viewmodels

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
    private var liveDataWeekIncValue = MutableLiveData<String>()
    private var liveDataWeekConsValue = MutableLiveData<String>()
    private var liveDataMonthIncValue = MutableLiveData<String>()
    private var liveDataMonthConsValue = MutableLiveData<String>()
    private var liveDataAllIncValue = MutableLiveData<Double>()
    private var liveDataAllConsValue = MutableLiveData<Double>()
    private var liveDataBalanceValue = MutableLiveData<Double>()
    private var liveDataChosenDay = MutableLiveData<String>()

    // В блоке init запускаем функцию чтения с базы данных всех Item
    init {
        liveDataAllIncList.value?.clear()
//        onGetAllIncItemFromDb()
        liveDataAllConsList.value?.clear()
//        onGetAllConsItemFromDb()
        onGetAllItemFromDb()
        liveDataWeekIncList.value?.clear()
        onGetWeekIncItemFromDb()
        liveDataWeekConsList.value?.clear()
        onGetWeekConsItemFromDb()
        liveDataMonthIncList.value?.clear()
        onGetMonthIncItemFromDb()
        liveDataMonthConsList.value?.clear()
        onGetMonthConsItemFromDb()
    }

    //Записываем в LiveData значение списков, полученных из БД
    //и считаем итоговые расходы и доходы согласно периодам
    private fun onGetAllItemFromDb() {
        viewModelScope.launch {
            val itemIncList = loadListAllIncItemUseCase.execute() as ArrayList
            val itemConsList = loadListAllConsItemUseCase.execute() as ArrayList
            liveDataAllIncList.value = itemIncList
            liveDataAllConsList.value = itemConsList
            liveDataBalanceValue.value = onCalculated(itemIncList,itemConsList)
        }
    }

    private fun onGetWeekIncItemFromDb(){
        viewModelScope.launch {
            val itemList = loadListWeekIncItemUseCase.execute() as ArrayList
            liveDataWeekIncList.value= itemList
            liveDataWeekIncValue.value = onCalculatedIncCons(itemList).toString()

        }
    }
    private fun onGetWeekConsItemFromDb(){
        viewModelScope.launch {
            val itemList = loadListWeekConsItemUseCase.execute() as ArrayList
            liveDataWeekConsList.value = itemList
            liveDataWeekConsValue.value = onCalculatedIncCons(itemList).toString()
        }
    }
    private fun onGetMonthIncItemFromDb(){
        viewModelScope.launch {
            val itemList = loadListMonthIncItemUseCase.execute() as ArrayList
            liveDataMonthIncList.value= itemList
            liveDataMonthIncValue.value = onCalculatedIncCons(itemList).toString()
        }
    }
    private fun onGetMonthConsItemFromDb(){
        viewModelScope.launch {
            val itemList = loadListMonthConsItemUseCase.execute() as ArrayList
            liveDataMonthConsList.value = itemList
            liveDataMonthConsValue.value = onCalculatedIncCons(itemList).toString()
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
//                onGetAllIncItemFromDb()
//                onGetAllConsItemFromDb()
                onGetAllItemFromDb()
                onGetMonthIncItemFromDb()
                onGetMonthConsItemFromDb()
                onGetWeekIncItemFromDb()
                onGetWeekConsItemFromDb()
//                onGetBalanceValue()
            }
        }
    //Здесь сохраняем дату, полученную с календаря
    fun onSaveChosenDay(chosenDay: String) {
        liveDataChosenDay.value = chosenDay
    }

    //Эти функции возвращают списки доходов и расходов в
    // зависимости от выбранного периода
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
    fun onLoadBalanceValue():LiveData<Double>{
        return liveDataBalanceValue
    }

    //Эти функции возвращают значения общих сумм расходов и доходов
    //в зависимости от выбранного периода
    fun onLoadAllIncValueLiveData():LiveData<Double>{
        return liveDataAllIncValue
    }
    fun onLoadAllConsValueLiveData():LiveData<Double>{
        return liveDataAllConsValue
    }
    fun onLoadWeekIncValueLiveData():LiveData<String>{
        return liveDataWeekIncValue
    }
    fun onLoadWeekConsValueLiveData():LiveData<String>{
        return liveDataWeekConsValue
    }
    fun onLoadMonthIncValueLiveData():LiveData<String>{
        return liveDataMonthIncValue

    }
    fun onLoadMonthConsValueLiveData():LiveData<String>{
        return liveDataMonthConsValue
    }

    //Возвращает значение дня, выбранного в календаре
    fun onLoadChosenDay(): LiveData<String> {
      return liveDataChosenDay
  }

    private fun onCalculatedIncCons(itemList:List<DayItem>):Double{
        var sum = 0.0
        for(i in 0..<itemList.size){
            sum += itemList[i].incomeConsumption
        }
        return sum
    }
    private fun onCalculated(itemIncList:List<DayItem>,itemConsList:List<DayItem>):Double{
        var sumInc = 0.0
        var sumCons = 0.0
        for(i in 0..<itemIncList.size){
            sumInc += itemIncList[i].incomeConsumption
        }
        for(i in 0..<itemConsList.size){
            sumCons += itemConsList[i].incomeConsumption
        }
        liveDataAllIncValue.value=sumInc
        liveDataAllConsValue.value=sumCons
        return sumInc-sumCons
    }
}