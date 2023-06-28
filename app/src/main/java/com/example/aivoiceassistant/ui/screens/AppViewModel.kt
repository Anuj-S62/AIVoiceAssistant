package com.example.aivoiceassistant.ui.screens

import android.app.Application
import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivoiceassistant.features.getDateTime
import com.example.aivoiceassistant.features.makeCall
import com.example.aivoiceassistant.features.openAnotherApp
import com.example.aivoiceassistant.features.sendEmial
import com.example.aivoiceassistant.features.setAlarm
import com.example.aivoiceassistant.features.setVolume
import com.example.aivoiceassistant.features.silentMode
import com.example.aivoiceassistant.features.toggleTorch
//import com.example.aivoiceassistant.features.toggleWifi
import com.example.aivoiceassistant.model.ResponseModel
import com.example.aivoiceassistant.model.TitleModel
import com.example.aivoiceassistant.model.UserDataModel
import com.example.aivoiceassistant.network.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.Locale


//sealed interface AppUiState{
//    data class Success(val response : String) : AppUiState
//}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class AppViewModel(application: Application) : AndroidViewModel(application) {
    var _userDataState = MutableStateFlow(UserDataModel())
        private set
    var _responseState = MutableStateFlow(ResponseModel())
        private set
    var _titleState = MutableStateFlow(TitleModel())
    var responseState : StateFlow<ResponseModel> =_responseState.asStateFlow()
    var userDataState : StateFlow<UserDataModel> = _userDataState.asStateFlow()
    var titleState : StateFlow<TitleModel> = _titleState.asStateFlow()
    var context = application as Context

    init {
        viewModelScope.launch {
            launch { animateUserQuery() }
            launch { animateTitle() }
        }
//        setAlarm(context)
    }

    suspend fun animateTitle(){
        var s:String = "AI Voice Assistant"
        var temp:String = ""
        var i:Int = 0
        while(i < s.length){
            temp += s[i]
            _titleState.update {
                c->c.copy(
                    title = temp
                )
            }
            i++
            delay(100)
        }
    }
    suspend fun animateUserQuery(){
        var s : String = userDataState.value.text;
        var temp = "";
        var i: Int = 0
        while(i<s.length){
            temp += s[i];
            _userDataState.update {
                c->c.copy(
                    t = temp
                )
            }
            i++;
            delay(70)
        }
    }
    suspend fun animateResponse(){
        var s : String = responseState.value.intent;
        var temp = "";
        var i: Int = 0
        while(i<s.length){
            temp += s[i];
            _responseState.update {
                    c->c.copy(
                t = temp
            )
            }
            i++;
            delay(60)
        }
    }

    fun performSR(res:String){
        var s:String = ""
        var i:Int = 1;
        while(i<res.length -1){
            s += res[i];
            i++;
        }
        _userDataState.update {
                c->c.copy(
            text = s
        )
        }
        viewModelScope.launch {
            launch { animateUserQuery() }
        }
        viewModelScope.launch {
            getResponse(s)
            animateResponse()
            if(responseState.value.intent=="audio_volume_up"){
                setVolume(context,responseState.value.action.toString().toInt(),responseState.value.extra)
                Log.d("1","8")
            }
            if(responseState.value.intent=="audio_volume_down"){
                if(responseState.value.extra=="by"){
                    setVolume(context,(responseState.value.action.toString().toInt())*-1,responseState.value.extra)
                }
                else{
                    setVolume(context,responseState.value.action.toString().toInt(),responseState.value.extra)
                }
            }
            if(responseState.value.intent=="audio_volume_mute"){
                silentMode(context)
            }
            if(responseState.value.intent=="open_app"){
                openAnotherApp(context,responseState.value.action)
            }
            if(responseState.value.intent=="email_addcontact"){
                openAnotherApp(context,"contacts")
            }
            if(responseState.value.intent=="calendar_set"){
                openAnotherApp(context,"calendar")
            }
            if(responseState.value.intent=="play_music"){
                openAnotherApp(context,"playstore")
            }
            if (responseState.value.intent=="email_sendemail"){
                sendEmial(context,responseState.value.action)
            }
            if(responseState.value.intent=="email_querycontact"){
                if(responseState.value.action!="No phone number found"){
                    makeCall(context, number = responseState.value.action)
                }

            }
        }

    }
    suspend fun getResponse(query:String) {
        val fields: HashMap<String?, RequestBody?> = HashMap()
        fields["text"] = (query).toRequestBody("text/plain".toMediaTypeOrNull())

        val response = ApiService.retrofitService.getIntent(fields)
        if (response.isSuccessful) {
            val responseBody = response.body()?.string()
            if (!responseBody.isNullOrEmpty()) {
                val gson = Gson()
                val mapType = object : TypeToken<Map<String, Any>>() {}.type
                val resultMap: Map<String, Any> = gson.fromJson(responseBody, mapType)
                Log.d("Response Map:", resultMap.toString())
                _responseState.update {
                        curr->curr.copy(
                    intent = resultMap["intent"].toString(),
                    action = resultMap["intentVal"].toString(),
                    response = resultMap["response"].toString(),
                    extra = resultMap["extraVal"].toString(),
                    flag = true
                )
                }

                Log.d("API Response : ",_responseState.value.response!!)
                Log.d("API Response : ",_responseState.value.intent!!)
                Log.d("API Response : ",_responseState.value.action!!)
                // Access the values using keys
                val value = resultMap["intent"].toString()
            }

        } else {
            _responseState.update { curr ->
                curr.copy(
                    flag = false,
                    intent = "Sorry I Can't Understand please try again after some time ",
                    action = "",
                    response = ""
                )
            }
            Log.e("RETROFIT_ERROR", response.code().toString())
        }
    }
}