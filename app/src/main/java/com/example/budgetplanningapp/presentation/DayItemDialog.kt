package com.example.budgetplanningapp.presentation

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.budgetplanningapp.R
import com.example.budgetplanningapp.domain.models.DayItem
import java.util.Calendar

class DayItemDialog(private val listener:Listener):DialogFragment() {
    private lateinit var btnOk:Button
    private lateinit var btnClear:Button
    private lateinit var edInc:EditText
    private lateinit var edCon:EditText
    private lateinit var tvDate:TextView
    private var calendarDate: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        val viewDialog=view
        if(viewDialog!=null){
           btnOk= view?.findViewById(R.id.btnOk)!!
           btnClear = view?.findViewById(R.id.btnClear)!!
           edInc = view?.findViewById(R.id.edInc)!!
           edCon = view?.findViewById(R.id.edCon)!!
           tvDate = view?.findViewById(R.id.tvDate)!!
           val date = DateUtils.formatDateTime(
               context,
               calendarDate.getTimeInMillis(),
               DateUtils.FORMAT_SHOW_YEAR)
           tvDate.text = date

           btnOk.setOnClickListener{

               if(edInc.text.toString()!=""&&edCon.text.toString()!=""){
                   val inc = edInc.text.toString().toDouble()
                   val con = edCon.text.toString().toDouble()
                   val profit = inc-con
                   val item = DayItem(date,inc,con,profit)
                   listener.onClick(item)
                   dismiss()
               }else {
                   if(edInc.text.toString()=="")edInc.setHint(R.string.inc_value)
                   if(edCon.text.toString()=="")edCon.setHint(R.string.con_value)
               }

           }
           btnClear.setOnClickListener{
               edInc.setText("")
               edCon.setText("")
           }

        }
    }


    interface Listener{
        fun onClick(item: DayItem)
    }
}
