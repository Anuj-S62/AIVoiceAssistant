package com.example.aivoiceassistant

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.provider.Settings
import androidx.camera.core.impl.utils.ContextUtil.getApplicationContext


class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        println("Alarm triggered: $message")
        try {
//             Get the default alarm sound URI
            val defaultAlarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

            // Create a MediaPlayer object
            val mediaPlayer = MediaPlayer()

            // Set the audio attributes for the MediaPlayer (optional, but recommended)
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
            mediaPlayer.setAudioAttributes(audioAttributes)

            // Set the data source of the MediaPlayer to the default alarm URI
            mediaPlayer.setDataSource(context, defaultAlarmUri)

            // Set audio stream type (adjust the volume accordingly)
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: Exception) {
            // Handle exceptions, if any
            e.printStackTrace()
        }
    }
}