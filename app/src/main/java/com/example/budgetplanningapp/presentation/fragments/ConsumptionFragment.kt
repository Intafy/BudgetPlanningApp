package com.example.budgetplanningapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.budgetplanningapp.databinding.FragmentIncomeConsumptionBinding
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.presentation.DayItemDialog
import com.example.budgetplanningapp.presentation.fragments.chosenfragments.AllTimeFragment
import com.example.budgetplanningapp.presentation.fragments.chosenfragments.MonthFragment
import com.example.budgetplanningapp.presentation.fragments.chosenfragments.WeekFragment
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator


class ConsumptionFragment(private val typeItem:String) : Fragment(),DayItemDialog.Listener {
    private lateinit var binding: FragmentIncomeConsumptionBinding
    private val fListChoosePeriodFragments = listOf(
        WeekFragment.newInstance(typeItem = typeItem),
        MonthFragment.newInstance(typeItem=typeItem),
        AllTimeFragment.newInstance(typeItem=typeItem)
    )
    private lateinit var model: MainViewModel
    private val listChoosePeriodName = listOf(
        "За неделю",
        "За месяц",
        "За все время"
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIncomeConsumptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        binding.btnAdd.setOnClickListener {
            val itemDialog = DayItemDialog(this,typeItem)
            itemDialog.show(childFragmentManager, "itemDialog")
        }
    }
    private fun init()=with(binding){
        model = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val adapter = FragmentAdapter(activity as FragmentActivity,fListChoosePeriodFragments)
        vpChoosePeriod.adapter=adapter
        TabLayoutMediator(tabChoosePeriod,vpChoosePeriod){
                tab,pos -> tab.text = listChoosePeriodName[pos]
        }.attach()
    }
    companion object {

        @JvmStatic
        fun newInstance(typeItem: String) = ConsumptionFragment(typeItem = typeItem)

    }

    override fun onClick(item: DayItem) {
        model.onSaveItemToDb(item)
    }
}