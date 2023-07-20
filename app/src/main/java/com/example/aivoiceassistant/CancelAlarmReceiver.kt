package com.example.aivoiceassistant

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaActionSound
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.util.Log
import com.example.aivoiceassistant.features.alarm.AlarmItem
import com.example.aivoiceassistant.features.alarm.AndroidAlarmScheduler
import java.time.LocalDate
import java.time.LocalTime

class CancelAlarmReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("media", mediaPlayer?.isPlaying().toString())
        if(mediaPlayer!!.isPlaying()){
            mediaPlayer?.release()
        }
        Intent(context,AlarmNotificationService::class.java).also {
            it.action = AlarmNotificationService.Actions.STOP.toString()
            context?.stopService(it)
        }
    }
}