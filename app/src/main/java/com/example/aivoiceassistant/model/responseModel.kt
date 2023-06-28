package com.example.aivoiceassistant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel (
    val intent:String = "",
    val action: String = "",
    val response: String = "",
    val extra:String = "",
    val flag: Boolean = false,
    val t:String = ""
    )