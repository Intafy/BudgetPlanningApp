package com.example.budgetplanningapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetplanningapp.R

class DayAdapter(private val listItem:ArrayList<DayItem>): RecyclerView.Adapter<DayAdapter.ItemHolder>() {


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
}