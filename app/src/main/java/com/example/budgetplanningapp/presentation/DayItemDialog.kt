package com.example.budgetplanningapp.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.budgetplanningapp.R
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar

class DayItemDialog(private var listener:Listener):DialogFragment() {
    private lateinit var model:MainViewModel
    private lateinit var btnOk: Button
    private lateinit var btnClear: Button
    private lateinit var btnCalendar: ImageView
    private lateinit var edInc: EditText
    private lateinit var edCon: EditText
    private lateinit var tvDate: TextView
    private var calendar: Calendar = Calendar.getInstance()
    private lateinit var calendarDate: String
    private lateinit var formattedDate: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        val viewDialog = view
        if (viewDialog != null) {
            onFormattedDate()
            model = ViewModelProvider(requireActivity())[MainViewModel::class.java]
            btnOk = view?.findViewById(R.id.btnOk)!!
            btnClear = view?.findViewById(R.id.btnClear)!!
            btnCalendar = view?.findViewById(R.id.btnCalendar)!!
            edInc = view?.findViewById(R.id.edInc)!!
            edCon = view?.findViewById(R.id.edCon)!!
            tvDate = view?.findViewById(R.id.tvDate)!!
            tvDate.text = calendarDate
            btnOk.setOnClickListener {

                if (edInc.text.toString() != "" && edCon.text.toString() != "") {
                    val inc = edInc.text.toString().toDouble()
                    val con = edCon.text.toString().toDouble()
                    val profit = inc - con
                    //Здесь передаем отформатированную дату
                    val item = DayItem(formattedDate, inc, con, profit)
                    listener.onClick(item)
                    dismiss()
                } else {
                    if (edInc.text.toString() == "") edInc.setHint(R.string.inc_value)
                    if (edCon.text.toString() == "") edCon.setHint(R.string.con_value)
                }

            }
            btnClear.setOnClickListener {
                edInc.setText("")
                edCon.setText("")
            }
            model.onLoadChosenDay().observe(viewLifecycleOwner){
                tvDate.text = calendarDate
                Log.d("MyLog","calendarDate: $calendarDate")

            }

            val dateListener = DatePickerDialog.OnDateSetListener { view,year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DATE, dayOfMonth)
                    calendarDate=String.format("%02d.%02d.%04d",dayOfMonth,monthOfYear+1,year)
                    model.onSaveChosenDay(calendarDate)
                    formattedDate = String.format("%04d-%02d-%02d",year,monthOfYear+1,dayOfMonth)
                }
            btnCalendar.setOnClickListener {
                context?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        R.style.DialogTheme,
                        dateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                    ).show()
                }
            }
        }
    }

    //Получаем значение дня, месяца и года из календаря на текущий день
//и преобразуем ее в формат для отображения в заголовке диалогового окна
//и для отправки в БД соответственно
    private fun onFormattedDate() {
        val day = calendar.get(Calendar.DATE).toString()
        val month = (calendar.get(Calendar.MONTH) + 1).toString()
        val year = (calendar.get(Calendar.YEAR)).toString()
        calendarDate = "$day.$month.$year"
        formattedDate = "$year-$month-$day"
        Log.d("MyLog", "date: $day")
        Log.d("MyLog", "month: $month")
        Log.d("MyLog", "year: $year")
    }

    interface Listener {
        fun onClick(item: DayItem)
    }
}


