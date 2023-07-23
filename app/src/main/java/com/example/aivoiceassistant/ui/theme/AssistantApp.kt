package com.example.aivoiceassistant.ui.theme

//import AlarmNotificationService
import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.os.Build
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aivoiceassistant.AlarmNotificationService
import com.example.aivoiceassistant.services.AppService
import com.example.aivoiceassistant.ui.screens.AppViewModel
import com.example.aivoiceassistant.ui.screens.SpeechRecognizerScreen
import com.example.aivoiceassistant.ui.screens.UserQueryScreen
import com.example.aivoiceassistant.ui.screens.responseScreen
//import com.example.counter.AlarmNotificationService


//var title:String = ""
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistantApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val appViewModel: AppViewModel = viewModel()
    val responseState by appViewModel.responseState.collectAsState()
    val userDataState by appViewModel.userDataState.collectAsState()
    val titleState by appViewModel.titleState.collectAsState()
    val uiState by appViewModel.uiState.collectAsState()
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
        AssistantTopAppBar(titleState.title)
        UserQueryScreen(userDataState.t)
        if(responseState.intent!=null){
            responseScreen(responseState.t,responseState.res)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            SpeechRecognizerScreen()
        }
        Button(onClick = {
            val intent = Intent(context,AppService::class.java).also {
                it.action = AppService.Action.START.toString()
            }
            context.startForegroundService(intent)
        }) {
            Text("Start Service")
        }

    }
}

@Composable
fun AssistantTopAppBar(title:String) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 20.dp), horizontalArrangement = Arrangement.Center) {
        Text(text = title, style = MaterialTheme.typography.headlineSmall, color = Color.White)
    }
}
