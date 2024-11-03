package com.example.budgetplanningapp.presentation.fragments.Choosesfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetplanningapp.databinding.FragmentPeriodBinding
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.presentation.DayAdapter
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel

class WeekFragment(private val typeItem:String) : Fragment() {
    private lateinit var binding: FragmentPeriodBinding
    private lateinit var adapter: DayAdapter
    private lateinit var model: MainViewModel
    private val dayItemList: ArrayList<DayItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPeriodBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        model.onLoadWeekLiveData().observe(viewLifecycleOwner) {
                //Запустится когда изменится liveDataList
            Log.d("MyLog","observeWeek: $it")
            adapter.setList(it)
        }
    }

    private fun init(){
        adapter = DayAdapter(dayItemList,typeItem)
        binding.tvIncCon.text = typeItem
        binding.rcIncomeConsum.layoutManager = LinearLayoutManager(requireActivity())
        binding.rcIncomeConsum.adapter = adapter
    }

    companion object{
        fun newInstance(typeItem: String) = WeekFragment(typeItem = typeItem)
    }
}