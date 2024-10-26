package com.example.budgetplanningapp.presentation.fragments.Choosesfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.budgetplanningapp.R


class WeekFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = WeekFragment()
    }
}