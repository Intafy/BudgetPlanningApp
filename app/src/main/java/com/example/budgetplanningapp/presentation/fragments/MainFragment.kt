package com.example.budgetplanningapp.presentation.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.budgetplanningapp.databinding.FragmentMainBinding
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : androidx.fragment.app.Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val listIncCons = listOf(
        "Доходы",
        "Расходы"
    )
    private val fListIncomeConsumption:List<Fragment> = listOf(
        IncomeFragment.newInstance(typeItem = listIncCons[0]),
        ConsumptionFragment.newInstance(typeItem = listIncCons[1])
    )


    private lateinit var model:MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
//        binding.btnAdd.setOnClickListener {
//            val itemDialog = DayItemDialog(this)
//            itemDialog.show(childFragmentManager, "itemDialog")
//        }
        Log.d("MyLog", "Activity created")
    }

    private fun init(){

        model = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val adapter = FragmentAdapter(activity as FragmentActivity,fListIncomeConsumption)
        binding.vpMain.adapter=adapter
        TabLayoutMediator(binding.tabIncCons,binding.vpMain){
            tabIncCons,pos -> tabIncCons.text = listIncCons[pos]
        }.attach()

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

//    override fun onClick(item: DayItem) {
//
//        model.onSaveItemToDb(item)
//        Log.d("MyLog","Запись добавлена")
//
//    }
}