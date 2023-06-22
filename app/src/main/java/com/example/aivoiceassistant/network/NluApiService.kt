package com.example.aivoiceassistant.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import com.google.gson.GsonBuilder
//import com.google.gson.JsonParser
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
//import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap

private const val BASE_URL = "https://voicenlu.azurewebsites.net"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://voicenlu.azurewebsites.net")
        .build()


interface NluAPIService{
    @Multipart
    @POST("/getIntent")
    suspend fun getIntent(@PartMap map: HashMap<String?, RequestBody?>) : Response<ResponseBody>
}

object ApiService{
    val retrofitService : NluAPIService by lazy {
        retrofit.create(NluAPIService::class.java)
    }
}

