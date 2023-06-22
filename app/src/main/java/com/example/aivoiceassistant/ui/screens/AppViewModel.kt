package com.example.aivoiceassistant.ui.screens


import android.app.Application
import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivoiceassistant.features.setVolume
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


//sealed interface AppUiState{
//    data class Success(val response : String) : AppUiState
//}

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
//        getResponse()
        viewModelScope.launch {
            launch { animateUserQuery() }
            launch { animateTitle() }
        }
//        speechRecognition(context = app.applicationContext)
    }
//    performs SR
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
        viewModelScope.launch {
            getResponse(s)
            }
        }
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
            Log.d("ssms",userDataState.value.t)
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
        Log.d("ssdisjdims",responseState.value.intent)
        while(i<s.length){
            Log.d("ssdisjdims",responseState.value.t)
            temp += s[i];
            _responseState.update {
                    c->c.copy(
                t = temp
            )
            }
            i++;
            delay(70)
        }
    }
    fun getResponse(query:String) {
        val fields: HashMap<String?, RequestBody?> = HashMap()
        fields["text"] = (query).toRequestBody("text/plain".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {

            // Do the POST request and get response
            val response = ApiService.retrofitService.getIntent(fields)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.string()
                    if (!responseBody.isNullOrEmpty()) {
                        val gson = Gson()
                        val mapType = object : TypeToken<Map<String, Any>>() {}.type
                        val resultMap: Map<String, Any> = gson.fromJson(responseBody, mapType)
                        Log.d("Response Map:", resultMap.toString())
                        _responseState.update {
                            curr->curr.copy(
                                intent = resultMap["intentName"].toString(),
                                action = resultMap["intentValue"].toString(),
                                response = resultMap["response"].toString(),
                                flag = true
                            )
                        }

                        if(responseState.value.intent=="volume"){
                            Log.d("helloworld","widniw")
                            setVolume(context,resultMap["intentValue"].toString().toInt())
                        }
                        Log.d("dmi",_responseState.value.response!!)
                        Log.d("dmi",_responseState.value.intent!!)
                        Log.d("dmi",_responseState.value.action!!)
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
    }
}