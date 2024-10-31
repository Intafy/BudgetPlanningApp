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

    override suspend fun load(): List<ItemStorage> {

        return mainDb.getDao().getAllItems()
        }

    override suspend fun loadWeekItems(): List<ItemStorage> {
        return mainDb.getDao().getWeekItems()
    }
}
