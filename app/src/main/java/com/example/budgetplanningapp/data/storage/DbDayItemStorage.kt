package com.example.budgetplanningapp.data.storage

import android.content.Context
import com.example.budgetplanningapp.data.storage.database.ItemStorage
import com.example.budgetplanningapp.data.storage.database.MainDb


class DbDayItemStorage(context:Context): DayItemStorage {
    //Тут иммитируем базу данных и загрузку из нее и в нее информации (спискок ниже)

    private var listItem= arrayListOf(
        ItemStorage(1,"21.03.20",5.0,4.0,1111.0),
        ItemStorage(2,"22.03.20",7.0,3.0,5786.0),
        ItemStorage(3,"23.03.20",8.0,9.0,-7586.0),
        ItemStorage(4,"24.03.20",2.0,3.0,-833.0),
        ItemStorage(5,"25.03.20",6.0,1.0,727.0)
    )
    val mainDb=MainDb.getDb(context=context)
    override fun save(itemStorage: ItemStorage):Boolean {
//        listItem.add(itemStorage)
        mainDb.getDao().insertItem(itemStorage)
        return true
    }

    override fun load(): List<ItemStorage> {
        return listItem
    }
}