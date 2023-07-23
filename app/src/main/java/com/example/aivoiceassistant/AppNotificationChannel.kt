package com.example.aivoiceassistant

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build


class AlarmNotificationChannel: Application(){

    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val alarmChannel = NotificationChannel(
                "alarm_channel",
                "Alarm NOTIFICATION",
                NotificationManager.IMPORTANCE_HIGH
            )
            val appServiceChannel = NotificationChannel(
                "app_service_channel",
                "AI Voice NOTIFICATION",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(alarmChannel)
            notificationManager.createNotificationChannel(appServiceChannel)

        }
    }
}