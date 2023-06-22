package com.example.aivoiceassistant.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

@Composable
fun UserQueryScreen(userQuery:String){
    var backgroundColor = MaterialTheme.colorScheme.background
    var foregroundColor = MaterialTheme.colorScheme.primary

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 120.dp, end = 5.dp), horizontalArrangement = Arrangement.End) {
        Column(modifier = Modifier.padding(all = 5.dp)) {
            ExpandableBox(text = userQuery)
        }
    }
}

@Composable
fun ExpandableBox(text: String) {
    var expanded by remember { mutableStateOf(true) }

    val boxSize by animateDpAsState(
        if (expanded) (getSize(text)).dp else 10.dp,
        animationSpec = tween(durationMillis = 70, easing = LinearEasing)
    )

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(25.dp, 25.dp, 0.dp, 25.dp))
            .width(boxSize)
            .background(color = Color(red = 207, green = 245, blue = 162))
            .wrapContentSize()
            .animateContentSize()
            .padding(all = 10.dp)
    ) {
        Text(text,color = Color.Black)
    }
}
//
fun getSize(text: String):Int{
    if(text.length >= 25) return 250
    return 20 + text.length*10
}