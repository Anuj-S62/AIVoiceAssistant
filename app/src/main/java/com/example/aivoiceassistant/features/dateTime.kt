package com.example.aivoiceassistant.features

import android.content.Context
import android.util.Log
import java.util.Calendar

fun getDateTime(context: Context):String{
    var selectedTime : Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
    }
    Log.d("sknsk",selectedTime.timeZone.toString())
    Log.d("time",selectedTime.timeInMillis.toString())
    Log.d("date",selectedTime.time.minutes.toString())
    return selectedTime.time.toString()
}