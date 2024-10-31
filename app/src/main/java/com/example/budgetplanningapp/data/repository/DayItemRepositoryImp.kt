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
//        Log.d("MyLog","itemStorageList: ${itemStorageList}")
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
                        tempDayItemList.add(dayItem)
                    }
                }
            dayItemList = tempDayItemList
        return dayItemList
    }

    override suspend fun loadWeekItems(): List<DayItem> {
        itemStorageList = dayItemStorage.loadWeekItems()
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
                tempDayItemList.add(dayItem)
            }
        }
        dayItemList = tempDayItemList
        return dayItemList
    }

}