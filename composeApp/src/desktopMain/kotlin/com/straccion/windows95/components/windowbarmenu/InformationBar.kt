package com.straccion.windows95.components.windowbarmenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_measure
import windows95.composeapp.generated.resources.ic_volume
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun InformationBar() {

    var currentTime by remember {
        mutableStateOf(
            SimpleDateFormat("hh:mm a").format(Date())
        )
    }

    LaunchedEffect(key1 = Unit){
        while (true){
            currentTime = SimpleDateFormat("hh:mm a").format(Date())
            delay(1000)
        }
    }

    Row(modifier = Modifier
        .fillMaxHeight()
        .padding(horizontal = 2.dp, vertical = 4.dp)
        .border(BorderStroke(1.5.dp, Color.Gray)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(4.dp))
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(Res.drawable.ic_measure),
            contentDescription = "Imagen",
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.width(2.dp))
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(Res.drawable.ic_volume),
            contentDescription = "Imagen",
            contentScale = ContentScale.Fit
        )
        Text(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp),
            text = currentTime)

    }
}