package com.example.budgetplanningapp.data.repository

import android.util.Log
import com.example.budgetplanningapp.data.storage.DayItemStorage
import com.example.budgetplanningapp.data.storage.database.ItemStorage
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.repository.DayItemRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DayItemRepositoryImp(private val dayItemStorage: DayItemStorage): DayItemRepository {
    private  var dayItemList = listOf<DayItem>()

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

    override suspend fun loadAllItemFromDb(): List<DayItem> {
        val tempAllItemList = ArrayList<DayItem>()
        //Здесь из списка объектов модели ItemStorage , приходящей со слоя Storage,
        // мы информацию сохраняем в список объектов модели DayItem слоя Domain
        var itemStorageList:List<ItemStorage> = dayItemStorage.load()

        if(itemStorageList!=null){
                var dateFromDb:LocalDate
                val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                var formattedDate:String
                    for (i in itemStorageList.indices) {
                        dateFromDb = LocalDate.parse(itemStorageList[i].date)
                        formattedDate = formatter.format(dateFromDb)
                        val dayItem = DayItem(
                            date = formattedDate,
                            income = itemStorageList[i].income,
                            consumption = itemStorageList[i].consumption,
                            profit = itemStorageList[i].profit
                        )
                        tempAllItemList.add(dayItem)
                    }
                }
            dayItemList = tempAllItemList
            Log.d("MyLog","tempDayItemList: ${tempAllItemList}")
        return dayItemList
    }

    override suspend fun loadWeekItemsFromDb(): List<DayItem> {
        val itemStorageWeekList:List<ItemStorage> = dayItemStorage.loadWeekItemsFromDb()
        val tempWeekItemList = ArrayList<DayItem>()
        if(itemStorageWeekList!=null){
            var dateFromDb:LocalDate
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            var formattedDate:String
            for (i in itemStorageWeekList.indices) {
                dateFromDb = LocalDate.parse(itemStorageWeekList[i].date)
                formattedDate = formatter.format(dateFromDb)
                val dayItem = DayItem(
                    date = formattedDate,
                    income = itemStorageWeekList[i].income,
                    consumption = itemStorageWeekList[i].consumption,
                    profit = itemStorageWeekList[i].profit
                )
                tempWeekItemList.add(dayItem)
            }
        }
        Log.d("MyLog","tempDayItemListWeek: ${tempWeekItemList}")
        dayItemList = tempWeekItemList
        return dayItemList
    }
}