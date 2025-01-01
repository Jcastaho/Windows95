package com.straccion.windows95.windows95

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.straccion.windows95.components.windowbarmenu.WindowsBar
import com.straccion.windows95.components.windowbarmenu.WindowsBarMenuScreen
import com.straccion.windows95.extensions.clickableWithoutRipple

@Composable
fun Windows95Screen() {
    var showWindowsMenu by remember { mutableStateOf(false) }


    Column {
        Box(modifier = Modifier.fillMaxWidth().weight(1f).clickableWithoutRipple{showWindowsMenu = false}) {
            WindowsBarMenuScreen(showWindowsMenu)
        }
        WindowsBar{showWindowsMenu = !showWindowsMenu}

    }
}