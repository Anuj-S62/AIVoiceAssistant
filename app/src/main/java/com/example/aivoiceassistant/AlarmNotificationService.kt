package com.example.aivoiceassistant

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.getActivity
import android.app.PendingIntent.getBroadcast
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.aivoiceassistant.MainActivity
import com.example.aivoiceassistant.R

class AlarmNotificationService:Service(){

//    val notificationManager : NotificationManager? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("kadjakjkajskajksajaks","kdjskjdksjdkjskdjskjd")
        when(intent?.action){
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }
    fun start(){
        showNotification()
    }

    fun showNotification(){
        Log.d("service","service")

        val intent = Intent(applicationContext, MainActivity:: class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = getActivity(applicationContext, 0, intent, FLAG_IMMUTABLE)


        val notification: Notification =
            NotificationCompat.Builder(applicationContext, "alarm_channel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("title")
                .setAutoCancel(true)
//                .addAction(increaseAction)
                .setOnlyAlertOnce(true)
//                .addAction(R.drawable.ic_launcher_foreground,"-$dec",decreasePendingIntent)
                .setContentText("lsdskdl")
                .build()
        startForeground(124,notification)
    }


    enum class Actions{
        START,
        STOP
    }
}

