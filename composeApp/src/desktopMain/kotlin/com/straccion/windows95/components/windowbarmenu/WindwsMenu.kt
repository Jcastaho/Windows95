package com.straccion.windows95.components.windowbarmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.straccion.windows95.components.BackgroundComponent
import com.straccion.windows95.extensions.rotateVertically
import com.straccion.windows95.ui.backgroundComponent
import com.straccion.windows95.ui.windowsBarTextBackground
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.*
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_documents
import windows95.composeapp.generated.resources.ic_programs
import windows95.composeapp.generated.resources.ic_settings

@Composable
fun WindwsMenu(showSubMenu: (Float?) -> Unit) {
    BackgroundComponent(
        modifier = Modifier
            .height(360.dp)
    ) {
        Column {
            Row {
                Column {
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                            .fillMaxHeight()
                            .background(windowsBarTextBackground).padding(4.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            modifier = Modifier.rotateVertically(false),
                            text = "Windows95",
                            fontSize = 38.sp
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                ) {
                    WindowsMenuItem(
                        text = "Programs",
                        painter = painterResource(Res.drawable.ic_programs),
                        expandible = true
                    ) { showSubMenu(it) }
                    WindowsMenuItem(
                        text = "Documents",
                        painter = painterResource(Res.drawable.ic_documents), expandible = true
                    ) { showSubMenu(it) }
                    WindowsMenuItem(
                        text = "Setings",
                        painter = painterResource(Res.drawable.ic_settings), expandible = true
                    ) { showSubMenu(it) }
                    WindowsMenuItem(
                        text = "Find",
                        painter = painterResource(Res.drawable.ic_find), expandible = true
                    ) { showSubMenu(it) }
                    WindowsMenuItem(
                        text = "Help",
                        painter = painterResource(Res.drawable.ic_help)
                    ) { showSubMenu(it) }
                    WindowsMenuItem(
                        text = "Run...",
                        painter = painterResource(Res.drawable.ic_run)
                    ) { showSubMenu(it) }
                    //DIVIDER
                    WindowsMenuItem(
                        text = "Run...",
                        painter = painterResource(Res.drawable.ic_shutdown)
                    ) { showSubMenu(it) }
                }
            }
        }
    }


}