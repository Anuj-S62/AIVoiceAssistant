package com.example.aivoiceassistant

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aivoiceassistant.ui.screens.AppViewModel
import com.example.aivoiceassistant.ui.theme.AiVoiceAssistantTheme
import com.example.aivoiceassistant.ui.theme.AssistantApp
import java.util.Locale

class MainActivity : ComponentActivity() {
    var outputTxt by mutableStateOf("Click button for Speech text ")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AiVoiceAssistantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                ) {
//                    var apContext = applicationContext
                    AssistantApp()
//                    volumeButton()
                }
            }
        }
    }

//    @Composable
//    fun volumeButton(){
//        val appViewModel: AppViewModel = viewModel()
//        val t = remember { mutableStateOf("inc vol") }
//        val context = LocalContext.current
//        var text by remember { mutableStateOf("kdmd") }
//        text = outputTxt
//        appViewModel.performSR(text)
//        Box(modifier = Modifier
//            .clickable(onClick = {
//                text = speechRecognition(context)
//            })
//            .clip(CircleShape) ,contentAlignment = Alignment.Center
//        )
//        {
//            Text(text = , fontSize = 40.sp,fontWeight = FontWeight.Medium,color = Color(255, 120, 0))
//        }
//    }


//    fun speechRecognition(context: Context) : String {
//        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
//            Toast.makeText(context, "Speech not Available", Toast.LENGTH_SHORT).show()
//        } else {
//            //calling a speech recognizer intent
//            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//
//            //specifying language model as language web search
//            intent.putExtra(
//                RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
//            )
//
//            //specifying extra language as default english language
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//
//            //specifying prompt as Speak something
//            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Something")
//            val result = startActivityForResult(intent, 101)
//            return outputTxt
//        }
//        return ""
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
//
//        super.onActivityResult(requestCode, resultCode, data)
//        // on below line we are checking if the request
//        // code is same and result code is ok
//        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
//            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
//            // on below line we are setting result
//            // in our output text method.
//            outputTxt = result?.get(0).toString()
////            return result?.get(0).toString()
//
//        }
//
//    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AiVoiceAssistantTheme {
        Greeting("Android")
    }
}