package com.example.aivoiceassistant.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aivoiceassistant.features.setVolume
import kotlinx.coroutines.delay
import java.lang.Long.max

val givenText = "increase the volume of my phone"
var t :String = ""

@Composable
fun responseScreen(response :String,text2:String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 5.dp, end = 120.dp)) {
        Column(modifier = Modifier.padding(all = 5.dp)) {
            if(response.length > 0){
                ResponseExpandableBox(text = response,text2)
            }

        }
    }
}

@Composable
fun ResponseExpandableBox(text: String,text2:String) {

    var expanded by remember { mutableStateOf(true) }

    val boxSize by animateDpAsState(
        if (expanded) (getSize(kotlin.math.max(text.length,text2.length) + 40)).dp else 60.dp,
        animationSpec = tween(durationMillis = 40, easing = LinearEasing)
    )

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 0.dp))
            .background(color = Color(red = 255, green = 255, blue = 255))
            .width(boxSize)
            .wrapContentSize()
            .animateContentSize()
            .padding(all = 15.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text("Intent : "+text,color = Color.Black)
            Text(text2,color = Color.Black)
        }
    }
}

