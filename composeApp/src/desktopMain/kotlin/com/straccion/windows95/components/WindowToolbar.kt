package com.straccion.windows95.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.straccion.windows95.ui.backgroundComponent
import com.straccion.windows95.ui.disabledTextColor
import com.straccion.windows95.ui.windowsBlue
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_close
import windows95.composeapp.generated.resources.ic_expand
import windows95.composeapp.generated.resources.ic_minimize

@Composable
fun WindowToolbar(
    modifier: Modifier,
    title: String,
    selected: Boolean,
    onMinimize: (() -> Unit)? = null,
    onExpand: (() -> Unit)? = null,
    onClose: (() -> Unit)? = null,
) {
    val textColor = if (selected) White else disabledTextColor
    val backgroundColor = if (selected) windowsBlue else backgroundComponent

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp)
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = title, color = textColor)
        Spacer(Modifier.weight(1f))
        if (onMinimize != null) {
            WindowsButton(Modifier.size(20.dp), onClick = { onMinimize() }) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_minimize),
                        contentDescription = "minimize",
                        tint = Color.Black
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
        if (onExpand != null) {
            WindowsButton(Modifier.size(20.dp), onClick = { onExpand() }) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_expand),
                        contentDescription = "minimize",
                        tint = Color.Black
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
        if (onClose != null) {
            WindowsButton(Modifier.size(20.dp), onClick = { onClose() }) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_close),
                        contentDescription = "minimize",
                        tint = Color.Black
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
    }
}