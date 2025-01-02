package com.straccion.windows95.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.straccion.windows95.extensions.patternBackground
import com.straccion.windows95.model.WindowModel
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_folder_open

@Composable
fun WindowMinimizedItem(
    windowModel: WindowModel,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .padding(vertical = 3.dp, horizontal = 2.dp)
            .fillMaxHeight()
            .width(140.dp)
            .clickable { onClick() }
    ) {
        if (windowModel.minimized) {
            BackgroundComponent(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(1.dp))
                    Image(
                        modifier = Modifier.size(19.dp),
                        painter = painterResource(Res.drawable.ic_folder_open),
                        contentDescription = "icon"
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        windowModel.title,
                        style = TextStyle(lineHeight = 0.sp)
                    )
                }

            }
        } else {
            BackgroundComponent(selected = true, modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(2.dp).patternBackground(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(1.dp))
                    Image(
                        modifier = Modifier.size(19.dp),
                        painter = painterResource(Res.drawable.ic_folder_open),
                        contentDescription = "icon"
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        windowModel.title,
                        style = TextStyle(lineHeight = 0.sp)
                    )
                }
            }
        }
    }
}