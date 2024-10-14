package com.example.budgetplanningapp.data.storage



class DbDayItemStorage: DayItemStorage {
    //Тут иммитируем базу данных и загрузку из нее и в нее информации (спискок ниже)
    private var listItem= arrayListOf(
        ItemStorage("21.03.20",5.0,4.0,1111.0),
        ItemStorage("22.03.20",7.0,3.0,5786.0),
        ItemStorage("23.03.20",8.0,9.0,-7586.0),
        ItemStorage("24.03.20",2.0,3.0,-833.0),
        ItemStorage("25.03.20",6.0,1.0,727.0)
    )

    override fun save(itemStorage: ItemStorage):Boolean {
        listItem.add(itemStorage)
        return true
    }

    override fun load(): List<ItemStorage> {

        return listItem
    }
}