package com.example.aivoiceassistant.features

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.util.Log

@SuppressLint("ServiceCast")
fun setVolume(context: Context, volumeDelta: Int) {
    var vol = volumeDelta
    if(volumeDelta>100){
        vol = 100
    }
//    volumeDelta =
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
//    val newVolume = currentVolume + volumeDelta
    val newVolume = (vol*15)/100
    Log.d("maxVol",newVolume.toString())
    if (newVolume <= maxVolume) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, AudioManager.FLAG_SHOW_UI)
    } else {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, AudioManager.FLAG_SHOW_UI)
    }
}