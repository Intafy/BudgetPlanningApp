package com.example.budgetplanningapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.budgetplanningapp.databinding.FragmentIncomeBinding
import com.example.budgetplanningapp.presentation.fragments.сhoosesfragments.AllTimeFragment
import com.example.budgetplanningapp.presentation.fragments.сhoosesfragments.MonthFragment
import com.example.budgetplanningapp.presentation.fragments.сhoosesfragments.WeekFragment
import com.google.android.material.tabs.TabLayoutMediator


class IncomeFragment(private val typeItem:String) : Fragment() {

    private lateinit var binding: FragmentIncomeBinding
    private val fListChoosePeriodFragments = listOf(
        WeekFragment.newInstance(typeItem = typeItem),
        MonthFragment.newInstance(typeItem=typeItem),
        AllTimeFragment.newInstance(typeItem = typeItem)
    )
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
        binding = FragmentIncomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init()=with(binding){
        val adapter = FragmentAdapter(activity as FragmentActivity,fListChoosePeriodFragments)
        vpChoosePeriod.adapter=adapter
        TabLayoutMediator(tabChoosePeriod,vpChoosePeriod){
                tab,pos -> tab.text = listChoosePeriodName[pos]
        }.attach()
    }
    companion object {

        @JvmStatic
        fun newInstance(typeItem: String) = IncomeFragment(typeItem = typeItem)

    }
}