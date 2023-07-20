package com.example.aivoiceassistant


import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.getBroadcast
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.util.Log
import androidx.camera.core.impl.utils.ContextUtil.getApplicationContext
import androidx.core.app.NotificationCompat


class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
//        var mediaPlayer: MediaPlayer? = null
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
//        println("Alarm triggered: $message")
//        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        val dismissIntent = Intent(context,CancelAlarmReceiver::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            putExtra("time",message)
//        }
//        val pendingIntent = getBroadcast(
//            context!!,
//            intent.hashCode(),
//            dismissIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
//        )
//        val dismissAction : NotificationCompat.Action= NotificationCompat.Action(1,"Dismiss",pendingIntent)
//
//        val notification = Notification.Builder(context,"alarm_channel")
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle("Alarm triggered : $message")
//            .addAction(androidx.core.R.drawable.notification_bg,"Dismiss",pendingIntent)
//            .build()
//        notificationManager.notify(124,notification)
//        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
//        mediaPlayer = MediaPlayer.create(context,uri)
//        mediaPlayer!!.isLooping = true
//        mediaPlayer!!.start()
        Intent(context,AlarmNotificationService::class.java).also {
            it.action = AlarmNotificationService.Actions.START.toString()
            it.putExtra("time",message)
            it.putExtra("cancel",false)
            context.startService(it)
        }
        }
    }