package com.example.budgetplanningapp.data.repository


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
                incomeConsumption = dayItem.incomeConsumption,
                typeItem = dayItem.typeItem
            )
            result = dayItemStorage.save(itemStorage)
        return result
    }

    override suspend fun loadAllIncItemFromDb(): List<DayItem> {
        var itemStorageList:List<ItemStorage> = dayItemStorage.loadAllIncItem()
        dayItemList = onFormattedItem(itemStorageList)
        return dayItemList
    }

    override suspend fun loadAllConsItemFromDb(): List<DayItem> {
        var itemStorageList:List<ItemStorage> = dayItemStorage.loadAllConsItem()
        dayItemList = onFormattedItem(itemStorageList)
        return dayItemList
    }

    override suspend fun loadWeekIncItemFromDb(): List<DayItem> {
        val itemStorageWeekList:List<ItemStorage> = dayItemStorage.loadWeekIncItemFromDb()
        dayItemList = onFormattedItem(itemStorageWeekList)
        return dayItemList
    }

    override suspend fun loadWeekConsItemFromDb(): List<DayItem> {
        val itemStorageWeekList:List<ItemStorage> = dayItemStorage.loadWeekConsItemFromDb()
        dayItemList = onFormattedItem(itemStorageWeekList)
        return dayItemList
    }

    override suspend fun loadMonthIncItemFromDb(): List<DayItem> {
        val itemStorageMonthList:List<ItemStorage> = dayItemStorage.loadMonthIncItemFromDb()
        dayItemList = onFormattedItem(itemStorageMonthList)
        return dayItemList
    }

    override suspend fun loadMonthConsItemFromDb(): List<DayItem> {
        val itemStorageMonthList:List<ItemStorage> = dayItemStorage.loadMonthConsItemFromDb()
        dayItemList = onFormattedItem(itemStorageMonthList)
        return dayItemList
    }

    private fun onFormattedItem(itemStorageList: List<ItemStorage>):ArrayList<DayItem>{
        //Здесь из списка объектов модели ItemStorage , приходящей со слоя Storage,
        // мы информацию сохраняем в список объектов модели DayItem слоя Domain
        val tempAllItemList = ArrayList<DayItem>()
        if(itemStorageList!=null){
            var dateFromDb:LocalDate
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            var formattedDate:String
            for (i in itemStorageList.indices) {
                dateFromDb = LocalDate.parse(itemStorageList[i].date)
                formattedDate = formatter.format(dateFromDb)
                val dayItem = DayItem(
                    date = formattedDate,
                    incomeConsumption = itemStorageList[i].incomeConsumption,
                    typeItem = itemStorageList[i].typeItem,
                )
                tempAllItemList.add(dayItem)
            }
        }
        return tempAllItemList
    }
}