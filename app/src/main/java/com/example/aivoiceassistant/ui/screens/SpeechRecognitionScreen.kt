package com.example.aivoiceassistant.ui.screens

import ai.picovoice.porcupine.Porcupine
import ai.picovoice.porcupine.PorcupineManager
import ai.picovoice.porcupine.PorcupineManagerCallback
import android.Manifest
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aivoiceassistant.features.SpeechRecognizerContract
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpeechRecognizerScreen
            (viewModel: AppViewModel = viewModel()) {

    val permissionState = rememberPermissionState(
        permission = Manifest.permission.RECORD_AUDIO
    )
    SideEffect {
        permissionState.launchPermissionRequest()
    }

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = SpeechRecognizerContract(),
        onResult = {
            viewModel.performSR(it.toString())
        }
    )
    val context = LocalContext.current
    val wakeWordCallback = object : PorcupineManagerCallback {
        override fun invoke(keywordIndex: Int) {
            when (keywordIndex) {
                0 -> {
                    // porcupine detected
                    Log.d("porcupine","porcupine")
                    if (permissionState.status.isGranted) {
                        speechRecognizerLauncher.launch(Unit)
                    } else
                        permissionState.launchPermissionRequest()
                }

                1 -> {
                    // bumblebee detected
                    Log.d("porcupine","bumblebee")
                    if (permissionState.status.isGranted) {
                        speechRecognizerLauncher.launch(Unit)
                    } else
                        permissionState.launchPermissionRequest()


                }
            }
        }
    }
//    "${KEYWORD_FILE_PATH}"
    val porcupineManager = PorcupineManager.Builder()
        .setAccessKey("zacHLDfY8qGEoFMt3j9obQBUaF3BE+0FJ9MOmhMKBfJgR/AmUWJseQ==")
        .setKeyword(Porcupine.BuiltInKeyword.ALEXA)
        .build(context, wakeWordCallback)

    porcupineManager.start()
    Button(onClick = {speechRecognizerLauncher.launch(Unit)}) {
        Text("SR")
    }

}