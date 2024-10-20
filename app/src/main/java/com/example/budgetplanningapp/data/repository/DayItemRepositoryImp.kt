package com.example.budgetplanningapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData

import com.example.budgetplanningapp.data.storage.DayItemStorage
import com.example.budgetplanningapp.data.storage.database.ItemStorage
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.repository.DayItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class DayItemRepositoryImp(private val dayItemStorage: DayItemStorage): DayItemRepository {
     private  var dayItemList = MutableLiveData<List<DayItem>>()
     private lateinit var itemStorageList:LiveData<List<ItemStorage>>
     private var tempDayItemList = ArrayList<DayItem>()

    override suspend fun saveDayItemToDb(dayItem: DayItem): Boolean {
        //Здесь из модели объекта DayItem, приходящей со слоя Domain,
        // мы информацию сохраняем в объект модели ItemStorage слоя Storage
        var result = true
            val itemStorage = ItemStorage(
                date=dayItem.date,
                income = dayItem.income,
                consumption = dayItem.consumption,
                profit = dayItem.profit
            )
            Log.d("MyLog", "CoroutineScopeSave is running")
            result = dayItemStorage.save(itemStorage)
        return result
    }

    override fun loadDayItemListFromDb(): LiveData<List<DayItem>> {
        //Здесь из списка объектов модели ItemStorage , приходящей со слоя Storage,
        // мы информацию сохраняем в список объектов модели DayItem слоя Domain

                itemStorageList = dayItemStorage.load()
        Log.d("MyLog","dayItemStorage.load(): ${dayItemStorage.load().value}")

        Log.d("MyLog","itemStorageList: ${itemStorageList.value}")
                if(itemStorageList.value!=null){
                    for (i in 0..itemStorageList.value!!.size) {
                        val dayItem = DayItem(
                            date = itemStorageList.value!![i].date,
                            income = itemStorageList.value!![i].income,
                            consumption = itemStorageList.value!![i].consumption,
                            profit = itemStorageList.value!![i].profit
                        )
                        tempDayItemList.add(dayItem)
                        Log.d("MyLog", "dayItem: $dayItem")
                    }
                }
            dayItemList.value = tempDayItemList


        return dayItemList
    }
}