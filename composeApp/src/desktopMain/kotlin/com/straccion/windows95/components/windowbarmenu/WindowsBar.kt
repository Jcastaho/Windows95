package com.straccion.windows95.components.windowbarmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.straccion.windows95.components.WindowsButton
import com.straccion.windows95.ui.backgroundComponent
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.winlogo

@Composable
fun WindowsBar(onWindowsButtonSelected: () -> Unit) {
    Column {
        Box(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.White))
        Row(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(backgroundComponent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            WindowsButton(
                modifier = Modifier.height(36.dp).width(95.dp).padding(horizontal = 4.dp),
                onClick = { onWindowsButtonSelected() }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(Res.drawable.winlogo),
                        contentDescription = "Windows Logo",
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = "Start", fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(
                modifier = Modifier
                    .weight(1f)
            ) { }
            InformationBar()
        }
    }
}