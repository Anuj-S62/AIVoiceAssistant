package com.example.aivoiceassistant.features

import android.content.Context
import android.media.AudioManager
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

fun silentMode(context:Context){
    lateinit var audioManager: AudioManager
    var currentAudioMode = 0
    audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE

    // on the below line we are displaying a toast message as vibrate mode activated.
    Toast.makeText(context, "Vibrate mode activated..", Toast.LENGTH_LONG).show()
}