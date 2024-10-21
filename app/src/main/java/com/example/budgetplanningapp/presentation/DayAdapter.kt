package com.example.budgetplanningapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetplanningapp.R
import com.example.budgetplanningapp.domain.models.DayItem

class DayAdapter(private var listItem: ArrayList<DayItem>): RecyclerView.Adapter<DayAdapter.ItemHolder>() {

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
        holder.tvProfitValueOfDay.text = listItem[position].profit.toString()
    }
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDateOfDay:TextView = itemView.findViewById(R.id.tvDateOfDay)
        val tvProfitValueOfDay:TextView = itemView.findViewById(R.id.tvProfitValueOfDay)
    }
    fun setList(plistItem:ArrayList<DayItem>){
        Log.d("MyLog","plisitem: $plistItem")

        notifyDataSetChanged()
        listItem=plistItem
//        listItem= arrayListOf()
//        Log.d("MyLog","listItemAfterClear: $listItem")
//        for (i in plistItem.indices){
//            val item = DayItem(
//                date = plistItem[i].date,
//                income = plistItem[i].income,
//                consumption = plistItem[i].consumption,
//                profit = plistItem[i].profit
//            )
//            listItem.add(item)
//        }
        Log.d("MyLog","listItem: $listItem")
        notifyDataSetChanged()
    }
}