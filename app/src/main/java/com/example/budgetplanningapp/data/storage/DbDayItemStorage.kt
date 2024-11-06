package com.example.budgetplanningapp.data.storage

import android.content.Context
import com.example.budgetplanningapp.data.storage.database.ItemStorage
import com.example.budgetplanningapp.data.storage.database.MainDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DbDayItemStorage(context:Context): DayItemStorage {

    private val mainDb = MainDb.getDb(context = context)

    override suspend fun save(itemStorage: ItemStorage): Boolean = withContext(Dispatchers.IO) {

        mainDb.getDao().insertItem(itemStorage)
        return@withContext true
    }

    override suspend fun loadAllIncItem(): List<ItemStorage> {

        return mainDb.getDao().getAllIncItem()
        }
    override suspend fun loadAllConsItem(): List<ItemStorage> {

        return mainDb.getDao().getAllConsItem()
        }

    override suspend fun loadWeekIncItemFromDb(): List<ItemStorage> {
        return mainDb.getDao().getWeekIncItem()

    } override suspend fun loadWeekConsItemFromDb(): List<ItemStorage> {
        return mainDb.getDao().getWeekConsItem()
    }

    override suspend fun loadMonthIncItemFromDb(): List<ItemStorage> {
        return mainDb.getDao().getMonthIncItem()
    }

    override suspend fun loadMonthConsItemFromDb(): List<ItemStorage> {
        return mainDb.getDao().getMonthConsItem()
    }

}
