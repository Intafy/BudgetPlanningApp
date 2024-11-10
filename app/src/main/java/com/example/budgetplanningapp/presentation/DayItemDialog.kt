package com.example.budgetplanningapp.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.budgetplanningapp.R
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel
import java.util.Calendar

class DayItemDialog(private var listener:Listener,private  val typeItem:String):DialogFragment() {
    private lateinit var model: MainViewModel
    private lateinit var btnOk: Button
    private lateinit var btnClear: Button
    private lateinit var btnCalendar: ImageView
    private lateinit var edIncCon: EditText
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
    private fun init(){
        edIncCon = view?.findViewById(R.id.edIncCon)!!
        model = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        btnOk = view?.findViewById(R.id.btnOk)!!
        btnClear = view?.findViewById(R.id.btnClear)!!
        btnCalendar = view?.findViewById(R.id.btnCalendar)!!
        tvDate = view?.findViewById(R.id.tvDate)!!
    }
    override fun onStart() {
        super.onStart()
        val viewDialog = view
        if (viewDialog != null) {
            init()
            onFormattedDate()

            btnOk.setOnClickListener {
                if (edIncCon.text.toString() == "") edIncCon.setHint(R.string.enter_value)
                else{
                    val incCon = edIncCon.text.toString().toDouble()
                    val item = DayItem(
                        date=formattedDate,
                        incomeConsumption = incCon,
                        typeItem = typeItem)
                    listener.onClick(item)
                    dismiss()
                }
            }

            btnClear.setOnClickListener {
               edIncCon.setText("")
            }

            model.onLoadChosenDay().observe(viewLifecycleOwner) {
                 tvDate.text = calendarDate
            }

            val dateListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                   calendar.set(Calendar.YEAR, year)
                   calendar.set(Calendar.MONTH, monthOfYear)
                   calendar.set(Calendar.DATE, dayOfMonth)
                   calendarDate = String.format("%02d.%02d.%04d", dayOfMonth, monthOfYear + 1, year)
                   model.onSaveChosenDay(calendarDate)
                   formattedDate = String.format("%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)
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
        edIncCon.setHint(R.string.enter_value)
        val day = calendar.get(Calendar.DATE)
        val month = (calendar.get(Calendar.MONTH))
        val year = (calendar.get(Calendar.YEAR))
        calendarDate = String.format("%02d.%02d.%04d", day, month + 1, year)
        formattedDate = String.format("%04d-%02d-%02d", year, month + 1, day)
        tvDate.text = calendarDate
    }

    interface Listener {
        fun onClick(item: DayItem)
    }

}




