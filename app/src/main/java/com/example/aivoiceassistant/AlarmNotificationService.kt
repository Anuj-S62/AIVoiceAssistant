package com.example.aivoiceassistant

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.getActivity
import android.app.PendingIntent.getBroadcast
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.aivoiceassistant.MainActivity
import com.example.aivoiceassistant.R

class AlarmNotificationService:Service(){

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        mediaPlayer = MediaPlayer.create(applicationContext,uri)
        val time = intent?.getStringExtra("time")
        when(intent?.action){
            Actions.START.toString() -> start(time.toString())
            Actions.STOP.toString() -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun stop(){
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    private fun start(time:String){
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
        showNotification(time)
    }

    private fun showNotification(time:String){
        Log.d("service","service")

        val intent = Intent(applicationContext, CancelAlarmReceiver:: class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        intent.putExtra("time",time)
        intent.putExtra("cancel",true)
        val pendingIntent = getBroadcast(applicationContext, 0, intent, FLAG_IMMUTABLE)


        val notification = Notification.Builder(applicationContext,"alarm_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Alarm triggered : $time")
            .addAction(androidx.core.R.drawable.notification_bg,"Dismiss",pendingIntent)
            .build()
        startForeground(124,notification)
    }


    enum class Actions{
        START,
        STOP
    }
}

