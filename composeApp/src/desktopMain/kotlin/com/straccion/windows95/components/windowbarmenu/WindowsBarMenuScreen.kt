package com.straccion.windows95.components.windowbarmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.straccion.windows95.components.BackgroundComponent
import com.straccion.windows95.ui.backgroundComponent
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_programs

@Composable
fun WindowsBarMenuScreen(showWindowsMenu: Boolean) {

    var subBarMenuPosition by remember { mutableStateOf<Float?>(null) }

    if (showWindowsMenu) {
        Column {
            Spacer(Modifier.weight(1f))
            Row {
                WindwsMenu { subBarMenuPosition = it }
                subBarMenuPosition?.let {
                    BackgroundComponent(modifier = Modifier
                        .offset(y = it.dp)
                        .width(150.dp)){
                        Column{
                            WindowsMenuItem(
                                text = "Accesories",
                                painter = painterResource(Res.drawable.ic_programs),
                                isSubMenu = true
                            ){}
                            WindowsMenuItem(
                                text = "Accesories",
                                painter = painterResource(Res.drawable.ic_programs),
                                isSubMenu = true
                            ){}
                        }
                    }

                }
            }

        }
    }
}