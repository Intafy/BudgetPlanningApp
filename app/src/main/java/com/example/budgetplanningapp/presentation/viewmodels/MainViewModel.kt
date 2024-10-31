package com.example.budgetplanningapp.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.usecases.LoadListAllItemUseCase
import com.example.budgetplanningapp.domain.usecases.LoadListWeekItemUseCase
import com.example.budgetplanningapp.domain.usecases.SaveDayItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val loadListAllItemUseCase: LoadListAllItemUseCase,
    private val saveDayItemUseCase: SaveDayItemUseCase,
    private val loadListWeekItemUseCase: LoadListWeekItemUseCase
) :ViewModel() {
    // Создаем LiveData, которая будет хранить список полученных DayItem и присваиваем ей пустой список
    private var liveDataAllList = MutableLiveData<ArrayList<DayItem>>()
    private var liveDataWeekList = MutableLiveData<ArrayList<DayItem>>()
    private var liveDataChosenDay = MutableLiveData<String>()
    // В блоке init запускаем функцию чтения с базы данных всех Item
    init{
        liveDataWeekList.value?.clear()
        onGetWeekItemFromDB()
        liveDataAllList.value?.clear()
        onGetAllFromDb()
        Log.d("MyLog","VM created")
    }
//    Записываем в LiveData значение списка, полученного из БД
    private fun onGetAllFromDb(){
        viewModelScope.launch{
            liveDataAllList.value=loadListAllItemUseCase.execute() as ArrayList
            Log.d("MyLog","liveDataAllList: ${liveDataAllList.value}")
        }
    }
    private fun onGetWeekItemFromDB(){
        viewModelScope.launch {
            liveDataWeekList.value=loadListWeekItemUseCase.execute() as ArrayList
            Log.d("MyLog","liveDataWeekList: ${liveDataWeekList.value}")

        }
    }
    //Здесь сохраняем дату, полученную с календаря
    fun onSaveChosenDay(chosenDay:String){
        liveDataChosenDay.value = chosenDay
    }
    //Эти функции возвращают LiveData всем, кто будет подписан на нее
    fun onLoadLiveData():LiveData<ArrayList<DayItem>>{
        return liveDataAllList
    }
    fun onLoadWeekLiveData():LiveData<ArrayList<DayItem>>{
        return liveDataWeekList
    }
    //Возвращает значение дня, выбранного в календаре
    fun onLoadChosenDay():LiveData<String>{
        return liveDataChosenDay
    }
    //Эта функция сохраняет в БД новый Item
    fun onSaveItemToDb(item: DayItem){
        viewModelScope.launch(Dispatchers.IO) {
            //Очищаем значение LiveData, чтобы не дублировались в списке значения
            liveDataAllList.value!!.clear()
            liveDataWeekList.value!!.clear()
            saveDayItemUseCase.execute(item)
            //Делаем запрос к бд после сохранения
            onGetAllFromDb()
            onGetWeekItemFromDB()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyLog","Vm cleared")
    }
}