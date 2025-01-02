package com.straccion.windows95.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.straccion.windows95.model.FolderModel
import com.straccion.windows95.ui.windowsBlue
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_folder

@Composable
fun DraggableFolder(
    folderModel: FolderModel,
    onMove: (Offset) -> Unit,
    onTapFolder: (Int) -> Unit,
    onRename: (String) -> Unit,
    onDoubleTapFolder: (FolderModel) -> Unit
) {
    var offset by remember { mutableStateOf(folderModel.position) }

    val textColor = if (folderModel.selected) White else Black

    var newName by remember { mutableStateOf(folderModel.name) }
    var isEditing by remember { mutableStateOf(false) }
    var lastClickTime by remember { mutableStateOf(0L) }

    val focusReqester = remember { FocusRequester() }




    Box(
        Modifier.offset(x = folderModel.position.x.dp, y = folderModel.position.y.dp)
            .size(75.dp)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _, _ ->
                    offset = offset.copy(x = offset.x + pan.x, y = offset.y + pan.y)
                    onMove(offset)
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onTapFolder(folderModel.id)
                        val currentTime = System.currentTimeMillis()
                        val timeSinceLastClick = currentTime - lastClickTime
                        if (timeSinceLastClick in 250..900) {
                            isEditing = true
                        }
                        lastClickTime = currentTime
                    },
                    onPress = {
                        onTapFolder(folderModel.id)
                    },
                    onDoubleTap = {
                        onDoubleTapFolder(folderModel)
                    }
                )
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(50.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(Res.drawable.ic_folder),
                    contentDescription = "Folder"
                )
                if (folderModel.selected) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(Res.drawable.ic_folder),
                        contentDescription = "Folder",
                        tint = windowsBlue.copy(alpha = 0.4f)
                    )
                }
            }
            if (isEditing) {
                BasicTextField(
                    value = newName,
                    onValueChange = {newName = it},
                    modifier = Modifier.focusRequester(focusReqester),
                    singleLine = true,
                    maxLines = 1
                )
                LaunchedEffect(Unit){
                    focusReqester.requestFocus()
                }
            } else {
                Text(
                    modifier = Modifier.background(if (folderModel.selected) windowsBlue else Color.Transparent),
                    text = folderModel.name,
                    color = textColor
                )
            }
        }
        if (isEditing) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                isEditing = false
                                onRename(newName)
                            }
                        )
                    })
        }
    }
}