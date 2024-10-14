package com.example.budgetplanningapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetplanningapp.data.repository.DayItemRepositoryImp
import com.example.budgetplanningapp.data.storage.DbDayItemStorage
import com.example.budgetplanningapp.databinding.ActivityMainBinding
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.domain.usecases.LoadListDayItemUseCase
import com.example.budgetplanningapp.domain.usecases.SaveDayItemUseCase
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(),DayItemDialog.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DayAdapter
    private lateinit var model: MainViewModel


    private val dbDayItemStorage:DbDayItemStorage = DbDayItemStorage()
    private val dayItemRepositoryImp:DayItemRepositoryImp = DayItemRepositoryImp(dbDayItemStorage)
    private val loadListDayItemUseCase:LoadListDayItemUseCase = LoadListDayItemUseCase(dayItemRepositoryImp)
    private val saveDayItemUseCase:SaveDayItemUseCase = SaveDayItemUseCase(dayItemRepositoryImp)



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
    }
    private fun init(){
        adapter=DayAdapter(onGetDataList())
        binding.rcView.layoutManager=LinearLayoutManager(this)
        binding.rcView.adapter=adapter
        //model = ViewModelProvider(this)[MainViewModel::class.java]
    }
    private fun onGetDataList():ArrayList<DayItem>{
       return loadListDayItemUseCase.execute()
//        return model.onGetListDataItem()
    }
    override fun onClick(item: DayItem) {
        saveDayItemUseCase.execute(item)
        //Жесткий костыль, чтобы проверить
        //сохраняет ли в нашу импровизированную БД новое значение
        //Надо делать через viewModel observer Live Data
        init()
//        model.onAddItemToList(item)
    }
}