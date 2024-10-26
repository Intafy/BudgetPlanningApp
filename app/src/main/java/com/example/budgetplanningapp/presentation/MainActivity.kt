package com.example.budgetplanningapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetplanningapp.R
import com.example.budgetplanningapp.databinding.ActivityMainBinding
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.presentation.fragments.MainFragment
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModelFactory


class MainActivity : AppCompatActivity(),DayItemDialog.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DayAdapter
    private lateinit var model: MainViewModel
    private val dayItemList: ArrayList<DayItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction().replace(R.id.place_holder, MainFragment.newInstance())
            .commit()
//        init()
//        model.onLoadLiveData().observe(this) {
//                //Запустится когда изменится liveDataList
//            adapter.setList(it)
//        }


//    override fun onStart() {
//        super.onStart()
//        Log.d("MyLog", "Activity onStart")
//
//    }

//    override fun onResume() {
//        super.onResume()
//        Log.d("MyLog", "Activity onResume")
//
//    }
//    private fun init() {
//
        model = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]
//        adapter = DayAdapter(dayItemList)
//        binding.rcView.layoutManager = LinearLayoutManager(this)
//        binding.rcView.adapter = adapter
//
//    }
    }
    override fun onClick(item: DayItem) {

}
    }




