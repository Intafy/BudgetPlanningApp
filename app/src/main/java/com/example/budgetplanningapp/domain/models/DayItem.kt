package com.example.budgetplanningapp.domain.models

data class DayItem(
    val date:String,
    val income: Double,
    val consumption: Double,
    val profit: Double
)
