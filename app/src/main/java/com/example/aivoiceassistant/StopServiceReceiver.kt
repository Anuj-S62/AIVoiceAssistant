package com.example.aivoiceassistant

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.aivoiceassistant.services.AppService

class StopServiceReceiver:BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val intent = Intent(p0,AppService::class.java).apply {
            action = AppService.Action.STOP.toString()
        }
        p0?.stopService(intent)
    }
}