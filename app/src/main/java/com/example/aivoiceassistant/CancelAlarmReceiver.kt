package com.example.aivoiceassistant

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.aivoiceassistant.features.alarm.AlarmItem
import com.example.aivoiceassistant.features.alarm.AndroidAlarmScheduler
import java.time.LocalDate
import java.time.LocalTime

class CancelAlarmReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("cancel","receiver")
        val time = intent?.getStringExtra("time")
//        var alarmItem: AlarmItem? = null
        val scheduler = AndroidAlarmScheduler(context!!)

        val t = LocalTime.parse(time)
        val currentDate = LocalDate.now()

        val localDateTime = currentDate.atTime(t)
        val alarmItem = AlarmItem(
            time = localDateTime,
            message = time!!
        )
        Log.d("time",time)
        Log.d("time",localDateTime.toString())
        var obj = AndroidAlarmScheduler(context = context)
        obj.cancel(alarmItem)
    }
}