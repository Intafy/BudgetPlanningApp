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
     private  var dayItemList = listOf<DayItem>()
     private lateinit var itemStorageList:List<ItemStorage>
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

    override suspend fun loadDayItemListFromDb(): List<DayItem> {
        //Здесь из списка объектов модели ItemStorage , приходящей со слоя Storage,
        // мы информацию сохраняем в список объектов модели DayItem слоя Domain

                itemStorageList = dayItemStorage.load()
//        Log.d("MyLog","dayItemStorage.load(): ${dayItemStorage.load().value}")

        Log.d("MyLog","itemStorageList: ${itemStorageList}")
                if(itemStorageList!=null){
                    for (i in itemStorageList.indices) {
                        val dayItem = DayItem(
                            date = itemStorageList[i].date,
                            income = itemStorageList[i].income,
                            consumption = itemStorageList[i].consumption,
                            profit = itemStorageList[i].profit
                        )
                        tempDayItemList.add(dayItem)
                        Log.d("MyLog", "dayItem: $dayItem")
                    }
                }
            dayItemList = tempDayItemList


        return dayItemList
    }
}