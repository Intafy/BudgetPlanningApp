package com.example.budgetplanningapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetplanningapp.data.storage.database.MainDb
import com.example.budgetplanningapp.databinding.ActivityMainBinding
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModelFactory


class MainActivity : AppCompatActivity(),DayItemDialog.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DayAdapter
    private lateinit var model: MainViewModel
    private val dayItemList:ArrayList<DayItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainDb = MainDb.getDb(this)


        mainDb.getDao().getAllItems().asLiveData().observe(this) {

            for (i in it.indices) {
                val dayItem = DayItem(
                    date = it[i].date,
                    income = it[i].income,
                    consumption = it[i].consumption,
                    profit = it[i].profit
                )
                dayItemList.add(dayItem)
            }
            adapter.setList(dayItemList)
            Log.d("MyLog", "dayItemList: $dayItemList")
        }
        init()
//        model.onGetListDataItemFromDb()

        model.liveDataList.observe(this) {it
                //Запустится когда изменится liveDataList
                adapter.setList(it)
            }
        binding.btnAdd.setOnClickListener {
                val itemDialog = DayItemDialog(this)
                itemDialog.show(supportFragmentManager, "itemDialog")
            }
            Log.d("MyLog", "Activity created")
        }
    private fun init() {

        model = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]
        adapter = DayAdapter(dayItemList)
        adapter.setList(dayItemList)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = adapter

    }
    override fun onClick(item: DayItem) {
        model.onSaveItemToDb(item)
    }
}

