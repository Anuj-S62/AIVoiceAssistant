package com.example.aivoiceassistant.ui.theme

import android.annotation.SuppressLint
import android.app.Application
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aivoiceassistant.ui.screens.AppViewModel
import com.example.aivoiceassistant.ui.screens.SpeechRecognizerScreen
import com.example.aivoiceassistant.ui.screens.UserQueryScreen
import com.example.aivoiceassistant.ui.screens.responseScreen


//var title:String = ""
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistantApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val appViewModel: AppViewModel = viewModel()
    val responseState by appViewModel.responseState.collectAsState()
    val userDataState by appViewModel.userDataState.collectAsState()
    val titleState by appViewModel.titleState.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
        AssistantTopAppBar(titleState.title)
        UserQueryScreen(userDataState.t)
        if(responseState.intent!=null){
            responseScreen(responseState.t)
        }
        SpeechRecognizerScreen()
    }
}

@Composable
fun AssistantTopAppBar(title:String) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 20.dp), horizontalArrangement = Arrangement.Center) {
        Text(text = title, style = MaterialTheme.typography.headlineSmall)
    }
}
