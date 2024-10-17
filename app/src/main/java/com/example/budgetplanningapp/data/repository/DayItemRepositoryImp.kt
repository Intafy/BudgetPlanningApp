package com.example.budgetplanningapp.data.repository

import android.util.Log

import com.example.budgetplanningapp.data.storage.DayItemStorage
import com.example.budgetplanningapp.data.storage.database.ItemStorage
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.repository.DayItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class DayItemRepositoryImp(private val dayItemStorage: DayItemStorage): DayItemRepository {

    override fun saveDayItemToDb(dayItem: DayItem): Boolean {
        //Здесь из модели объекта DayItem, приходящей со слоя Domain,
        // мы информацию сохраняем в объект модели ItemStorage слоя Storage
        var result = true
        val itemStorage = ItemStorage(
            date=dayItem.date,
            income = dayItem.income,
            consumption = dayItem.consumption,
            profit = dayItem.profit
        )
        CoroutineScope(Dispatchers.Main).launch{
            result = dayItemStorage.save(itemStorage)

        }
        return result
    }

    override  fun loadDayItemListFromDb(): ArrayList<DayItem> {
        //Здесь из списка объектов модели ItemStorage , приходящей со слоя Storage,
        // мы информацию сохраняем в список объектов модели DayItem слоя Domain

        var dayItemList:ArrayList<DayItem> = arrayListOf()
        Log.d("MyLog","dayItemList: $dayItemList")

        CoroutineScope(Dispatchers.Main).launch {
            Log.d("MyLog", "Coroutine is running")
            val itemStorageList = dayItemStorage.load().toList()

            for(i in itemStorageList.indices){
                val dayItem = DayItem (
                    date=itemStorageList[i].date,
                    income = itemStorageList[i].income,
                    consumption = itemStorageList[i].consumption,
                    profit = itemStorageList[i].profit)
                dayItemList.add(dayItem)
                Log.d("MyLog","dayItem: $dayItem")
            }
        }
        return dayItemList
    }
}