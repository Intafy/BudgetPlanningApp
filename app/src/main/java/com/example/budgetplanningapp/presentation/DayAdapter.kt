package com.example.budgetplanningapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetplanningapp.R
import com.example.budgetplanningapp.domain.models.DayItem

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

        if(typeItem =="Доходы"){
            holder.tvDateOfDay.text = listItem[position].date
            holder.tvTypeEntry.text=typeItem
            holder.tvEntryValue.text = listItem[position].incomeConsumption.toString()
        }else if (typeItem == "Расходы"){
            holder.tvDateOfDay.text = listItem[position].date
            holder.tvTypeEntry.text=typeItem
            holder.tvEntryValue.text = listItem[position].incomeConsumption.toString()
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTypeEntry:TextView = itemView.findViewById(R.id.tvTypeEntry)
        val tvDateOfDay:TextView = itemView.findViewById(R.id.tvDateOfDay)
        val tvEntryValue:TextView = itemView.findViewById(R.id.tvEntryValue)
    }

    fun setList(plistItem:ArrayList<DayItem>){
        Log.d("MyLog","plisitem: $plistItem")
        listItem=plistItem
        Log.d("MyLog","listItem: $listItem")
        notifyDataSetChanged()
    }
}


