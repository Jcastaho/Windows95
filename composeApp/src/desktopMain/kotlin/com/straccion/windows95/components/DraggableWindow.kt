package com.straccion.windows95.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.straccion.windows95.model.WindowModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableWindow(
    windowModel: WindowModel,
    onMove: (Offset) -> Unit,
    onClose: () -> Unit,
    onMinimize: () -> Unit,
    onExpand: () -> Unit
) {
    var currentOffset by remember { mutableStateOf(windowModel.position) }
    val desity = LocalDensity.current
    var prevPosition by remember { mutableStateOf(windowModel.position) }

    BoxWithConstraints {
        val containerWithPx = with(desity) { maxWidth.toPx() }
        val containerHeigthPx = with(desity) { maxHeight.toPx() }


        val windowWithPx = if (windowModel.expanded) containerWithPx else with(desity) { windowModel.size.width.toPx() }
        val windowHeigthPx =
            if (windowModel.expanded) containerHeigthPx else with(desity) { windowModel.size.height.toPx() }

        LaunchedEffect(windowModel.expanded) {
            currentOffset = if (windowModel.expanded) {
                Offset(0f, 0f)
            } else {
                prevPosition
            }
            onMove(currentOffset)
        }


        BackgroundComponent(
            modifier = Modifier.then(if(windowModel.expanded)Modifier.fillMaxSize() else Modifier.size(windowModel.size)).offset {
                IntOffset(currentOffset.x.toInt(), currentOffset.y.toInt())
            }.onDrag(
                matcher = PointerMatcher.mouse(PointerButton.Primary),
                onDrag = { offset ->
                    if (!windowModel.expanded) {
                        val newX = (currentOffset.x + offset.x).coerceIn(0f, containerWithPx - windowWithPx)
                        val newY = (currentOffset.y + offset.y).coerceIn(0f, containerHeigthPx - windowHeigthPx)
                        val newOffSet = Offset(newX, newY)
                        currentOffset = newOffSet
                        prevPosition = newOffSet
                        onMove(newOffSet)
                    }
                }
            )) {
            Column {
                WindowToolbar(
                    modifier = Modifier.padding(4.dp),
                    windowModel.title,
                    windowModel.selected,
                    onMinimize = { onMinimize() },
                    onExpand = { onExpand() },
                    onClose = { onClose() }
                )
                Row {
                    Spacer(Modifier.width(10.dp))
                    Text(text = "File")
                    Spacer(Modifier.width(10.dp))
                    Text(text = "Edit")
                    Spacer(Modifier.width(10.dp))
                    Text(text = "View")
                    Spacer(Modifier.width(10.dp))
                    Text(text = "Help")
                }
                BackgroundComponent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 5.dp),
                    selected = true
                ) {
                    Box(modifier = Modifier.fillMaxSize().background(Color.White))
                }
            }
        }
    }
}