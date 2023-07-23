package com.example.aivoiceassistant.services

import ai.picovoice.porcupine.Porcupine
import ai.picovoice.porcupine.PorcupineManager
import ai.picovoice.porcupine.PorcupineManagerCallback
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.core.app.NotificationCompat
import com.example.aivoiceassistant.R
import com.example.aivoiceassistant.StopServiceReceiver

var TAG = "AppService"
class AppService : Service() {
//    val speechRecognizerLauncher
    private val speech: SpeechRecognizer by lazy { SpeechRecognizer.createSpeechRecognizer(applicationContext)}

    private lateinit var recognitionListener: RecognitionListener

    val wakeWordCallback = object : PorcupineManagerCallback {
        override fun invoke(keywordIndex: Int) {
            when (keywordIndex) {
                0 -> {
//                    startListening()
                    // porcupine detected
                    Log.d("porcupine","porcupine")
//                    if (permissionState.status.isGranted) {
//                        speechRecognizerLauncher.launch(Unit)
//                    } else
//                        permissionState.launchPermissionRequest()
                }

                1 -> {
                    // bumblebee detected
                    Log.d("porcupine","bumblebee")
//                    if (permissionState.status.isGranted) {
//                        speechRecognizerLauncher.launch(Unit)
//                    } else
//                        permissionState.launchPermissionRequest()


                }
            }
        }
    }
    //    "${KEYWORD_FILE_PATH}"
    val porcupineManager = PorcupineManager.Builder()
        .setAccessKey("zacHLDfY8qGEoFMt3j9obQBUaF3BE+0FJ9MOmhMKBfJgR/AmUWJseQ==")
        .setKeyword(Porcupine.BuiltInKeyword.ALEXA)
        .build(this, wakeWordCallback)

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            Action.START.toString()->start()
            Action.STOP.toString()->onDestroy()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG,"Destroyed")
        super.onDestroy()
        stopSelf()
        porcupineManager.stop()
        porcupineManager.delete()
    }
    private fun stop(){
        stopSelf()
    }
    private fun start(){
        porcupineManager.start()
        Log.d("Service","Service Started")
        showNotifications()

    }
    private fun showNotifications(){

        val intent = Intent(applicationContext, StopServiceReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(applicationContext,9,intent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notificationAction = NotificationCompat.Action(2,"STOP",pendingIntent)
        val notification = NotificationCompat.Builder(applicationContext,"app_service_channel")
            .setContentTitle("Service Started")
            .setSmallIcon(androidx.core.R.drawable.notification_action_background)
            .addAction(notificationAction)
            .build()
        startForeground(178,notification)
        Log.d("App Service","Notification")
    }

    enum class Action{
        START,
        STOP
    }





    private fun startListening(){
//        val speechRecognizer = speech.setRecognitionListener(this)
        recognitionListener = MyRecognitionListener()
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            speech.setRecognitionListener(recognitionListener)
        } else {
            // Handle error
        }

        val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
            putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.`package`)
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true)
            }
        }
        speech.startListening(recognizerIntent)
    }



}


class MyRecognitionListener : RecognitionListener {
    override fun onReadyForSpeech(params: Bundle?) {
        Log.d("MyRecognitionListener", "onReadyForSpeech")
    }

    override fun onBeginningOfSpeech() {
        Log.d("MyRecognitionListener", "onBeginningOfSpeech")
    }

    override fun onRmsChanged(rmsdB: Float) {
        Log.d("MyRecognitionListener", "onRmsChanged: $rmsdB")
    }

    override fun onBufferReceived(p0: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun onEndOfSpeech() {
        TODO("Not yet implemented")
    }

    // Other callback methods...

    override fun onError(error: Int) {
        Log.e("MyRecognitionListener", "onError: $error")
    }

    override fun onResults(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onPartialResults(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
        TODO("Not yet implemented")
    }
}