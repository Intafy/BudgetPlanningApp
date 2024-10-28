package com.example.budgetplanningapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetplanningapp.R
import com.example.budgetplanningapp.domain.models.DayItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DayAdapter(private var listItem: ArrayList<DayItem>,private val typeItem:String): RecyclerView.Adapter<DayAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_day,parent,false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
      return listItem.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.tvDateOfDay.text = listItem[position].date
        if(typeItem =="Доходы"){
            holder.tvTypeEntry.text=typeItem
            holder.tvEntryValue.text = listItem[position].income.toString()
        }else if (typeItem == "Расходы"){
            holder.tvTypeEntry.text=typeItem
            holder.tvEntryValue.text = listItem[position].consumption.toString()
        }

    }
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTypeEntry:TextView = itemView.findViewById(R.id.tvTypeEntry)
        val tvDateOfDay:TextView = itemView.findViewById(R.id.tvDateOfDay)
        val tvEntryValue:TextView = itemView.findViewById(R.id.tvEntryValue)
    }
    fun setList(plistItem:ArrayList<DayItem>){
        Log.d("MyLog","plisitem: $plistItem")

        notifyDataSetChanged()
        listItem=plistItem
        Log.d("MyLog","listItem: $listItem")
        notifyDataSetChanged()
    }
}


