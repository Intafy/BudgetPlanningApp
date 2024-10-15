package com.example.budgetplanningapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetplanningapp.data.repository.DayItemRepositoryImp
import com.example.budgetplanningapp.data.storage.DbDayItemStorage
import com.example.budgetplanningapp.databinding.ActivityMainBinding
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.usecases.LoadListDayItemUseCase
import com.example.budgetplanningapp.domain.usecases.SaveDayItemUseCase
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity(),DayItemDialog.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DayAdapter
    private lateinit var model: MainViewModel






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.btnAdd.setOnClickListener{
            val itemDialog = DayItemDialog(this,)
            itemDialog.show(supportFragmentManager,"itemDialog")
        }
        Log.d("MyLog","Activity created")
        model.onGetListDataItemFromDb()
        model.liveDataList.observe(this, {
            adapter.setList(it)
        })
    }
    private fun init(){
        model = ViewModelProvider(this,MainViewModelFactory(this))[MainViewModel::class.java]
        binding.rcView.layoutManager=LinearLayoutManager(this)
        binding.rcView.adapter=adapter
        adapter=DayAdapter()
    }

    override fun onClick(item: DayItem) {
        model.onSaveItemToDb(item)
    }
}