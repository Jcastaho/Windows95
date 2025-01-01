package com.straccion.windows95.components.windowbarmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import com.straccion.windows95.ui.backgroundComponent
import com.straccion.windows95.ui.windowsBlue
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_arrow

@Composable
fun WindowsMenuItem(
    text: String,
    painter: Painter,
    expandible: Boolean = false,
    isSubMenu: Boolean = false,
    showSubMenu: (Float?) -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val backgroundColor = if (isHovered) windowsBlue else backgroundComponent
    val accentColor = if (isHovered) Color.White else Color.Black

    var globalHeightPosition by remember { mutableStateOf<Float?>(null) }

    LaunchedEffect(isHovered) {
        when {
            isHovered && expandible -> {
                showSubMenu(globalHeightPosition)
            }
            expandible -> {}
            isHovered -> {
                showSubMenu(null)
            }
        }
    }

    Row(
        Modifier.hoverable(interactionSource)
            .onGloballyPositioned { coordinates ->
                globalHeightPosition = coordinates.positionInParent().y
            }
            .padding(4.dp)
            .fillMaxWidth()
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(6.dp))
        Image(
            modifier = Modifier.size(if (isSubMenu)25.dp else 40.dp),
            painter = painter,
            contentDescription = "Imagen",
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = text, color = accentColor)
        if (expandible) {
            Spacer(Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(12.dp),
                painter = painterResource(Res.drawable.ic_arrow),
                tint = accentColor,
                contentDescription = "Flecha"
            )
        }
    }
}