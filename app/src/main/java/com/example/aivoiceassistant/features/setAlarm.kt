package com.example.aivoiceassistant.features

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

fun setAlarm(context: Context) {
    Log.d("wdjs1","alarm set")
    var selectedTime : Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY,22)
        set(Calendar.MINUTE,30)
    }
    Log.d("sssmsds",selectedTime.toString())
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    alarmManager.set(
        AlarmManager.RTC_WAKEUP,
        selectedTime.timeInMillis,
        pendingIntent
    )
    Log.d("wdjs0","alarm set")
}

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("alarm","received")
    }
}