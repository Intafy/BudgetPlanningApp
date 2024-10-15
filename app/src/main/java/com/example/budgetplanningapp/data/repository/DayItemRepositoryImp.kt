package com.example.budgetplanningapp.data.repository


import android.content.Context
import com.example.budgetplanningapp.data.storage.DayItemStorage
import com.example.budgetplanningapp.data.storage.database.ItemStorage
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.repository.DayItemRepository

class DayItemRepositoryImp(private val dayItemStorage: DayItemStorage): DayItemRepository {

    override fun saveDayItemToDb(dayItem: DayItem): Boolean {
        //Здесь из модели объекта DayItem, приходящей со слоя Domain,
        // мы информацию сохраняем в объект модели ItemStorage слоя Storage
        val itemStorage = ItemStorage(
            date=dayItem.date,
            income = dayItem.income,
            consumption = dayItem.consumption,
            profit = dayItem.profit
        )
        val result = dayItemStorage.save(itemStorage)
        return result
    }

    override fun loadDayItemListFromDb(): ArrayList<DayItem> {
        //Здесь из списка объектов модели ItemStorage , приходящей со слоя Storage,
        // мы информацию сохраняем в список объектов модели DayItem слоя Domain
        val itemStorageList = dayItemStorage.load()
        val dayItemList:ArrayList<DayItem> = arrayListOf()
            for(i in 0..<itemStorageList.size){
                val dayItem = DayItem (
                    date=itemStorageList[i].date,
                    income = itemStorageList[i].income,
                    consumption = itemStorageList[i].consumption,
                    profit = itemStorageList[i].profit)
                dayItemList.add(dayItem)
            }
        return dayItemList
    }
}