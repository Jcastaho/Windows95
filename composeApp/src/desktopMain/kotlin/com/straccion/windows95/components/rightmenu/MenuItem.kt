package com.straccion.windows95.components.rightmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.straccion.windows95.extensions.clickableWithoutRipple
import com.straccion.windows95.ui.backgroundComponent
import com.straccion.windows95.ui.disabledTextColor
import com.straccion.windows95.ui.windowsBlue
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_arrow

@Composable
fun MenuItem(
    text: String,
    enable: Boolean = true,
    showSubMenu: Boolean = false,
    hovered: (Offset?) -> Unit,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val backgound = if (isHovered && enable) windowsBlue else backgroundComponent
    val textColor = if (isHovered) Color.White else Color.Black

    var subMenuPosition by remember { mutableStateOf(Offset(0f, 0f)) }

    LaunchedEffect(isHovered) {
        if (isHovered) {
            hovered(subMenuPosition)
        }
    }

    Row(
        Modifier.hoverable(interactionSource)
            .fillMaxWidth()
            .padding(4.dp)
            .background(backgound)
            .padding(4.dp)
            .clickableWithoutRipple {
                if (enable) {
                    onClick()
                }
            }
            .onGloballyPositioned { layoutCoordinates ->
                val position = layoutCoordinates.positionInParent()
                subMenuPosition = Offset(
                    x = position.x + layoutCoordinates.size.width.toFloat() + 10, y = position.y
                )

            }
    ) {
        Spacer(Modifier.width(20.dp))
        Text(text = text, color = if (enable) textColor else disabledTextColor, style = TextStyle(lineHeight = 0.sp))
        Spacer(Modifier.weight(1f))
        if (showSubMenu) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(12.dp),
                painter = painterResource(Res.drawable.ic_arrow),
                tint = textColor,
                contentDescription = "Flecha"
            )
        }
    }
}