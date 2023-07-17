package com.example.aivoiceassistant.features.alarm

import android.content.Context
import android.util.Log
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


fun setAlarm(context: Context,time:String,setReset:Int){
    var alarmItem: AlarmItem? = null
    val scheduler = AndroidAlarmScheduler(context)

    val t = LocalTime.parse(time)
    val currentDate = LocalDate.now()

    val localDateTime = currentDate.atTime(t)
    alarmItem = AlarmItem(
        time = localDateTime,
        message = time
    )
    if(setReset==0){
        alarmItem?.let(scheduler::schedule)
    }
    else{
        alarmItem?.let(scheduler::cancel)

    }

}