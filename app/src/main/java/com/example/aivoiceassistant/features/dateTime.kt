package com.example.aivoiceassistant.features

import android.content.Context
import android.util.Log
import java.util.Calendar

fun getDateTime(context: Context):String{
    var selectedTime : Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
    }
    Log.d("date",selectedTime.time.toString())
    return selectedTime.time.toString()
}