package com.example.budgetplanningapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetplanningapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),DayItemDialog.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DayAdapter
    private val model: MainViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter=DayAdapter(onGetDataList())
        binding.rcView.layoutManager=LinearLayoutManager(this)
        binding.rcView.adapter=adapter
        binding.btnAdd.setOnClickListener{
            val itemDialog = DayItemDialog(this,)
            itemDialog.show(supportFragmentManager,"itemDialog")
        }
    }

    private fun onGetDataList():ArrayList<DayItem>{
        return model.onGetListDataItem()
    }
    override fun onClick(item:DayItem) {
        model.onAddItemToList(item)
    }
}