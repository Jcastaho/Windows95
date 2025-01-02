package com.straccion.windows95.windows95

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import com.straccion.windows95.components.DraggableFolder
import com.straccion.windows95.components.DraggableWindow
import com.straccion.windows95.components.windowbarmenu.WindowsBar
import com.straccion.windows95.components.windowbarmenu.WindowsBarMenuScreen
import com.straccion.windows95.extensions.clickableWithoutRipple
import com.straccion.windows95.model.FolderModel
import com.straccion.windows95.model.WindowModel

@Composable
fun Windows95Screen() {
    var showWindowsMenu by remember { mutableStateOf(false) }
    val fakefolder = FolderModel(0, "Juan", Offset(x = 0f, y = 0f))
    val fakefolder2 = FolderModel(1, "Juan555", Offset(x = 0f, y = 80f))
    var folders by remember { mutableStateOf(listOf<FolderModel>(fakefolder, fakefolder2)) }
    var windows by remember { mutableStateOf(listOf<WindowModel>()) }


    Column {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f)
                .clickableWithoutRipple {
                    showWindowsMenu = false
                    folders = clearFolders(folders)
                }) {
            folders.forEach { folder ->
                DraggableFolder(
                    folder, onMove = { newPosition ->
                        folders = folders.map {
                            if (it.id == folder.id) it.copy(position = newPosition) else it
                        }
                    }, onTapFolder = {id ->
                        folders = folders.map {
                            it.copy(selected = id == it.id)
                        }
                    }, onRename = { newName ->
                        folders = folders.map {
                            if (it.id == folder.id) it.copy(name = newName) else it
                        }
                    },
                    onDoubleTapFolder = { selectedFolder ->
                        if (windows.any { it.id == selectedFolder.id }) {
                            windows = windows.map {
                                if (it.id == selectedFolder.id) it.copy(selected = true, minimized = false) else it
                            }
                        } else {
                            val extraPosition = windows.size * 10
                            val newWindow = WindowModel(
                                id = selectedFolder.id,
                                title = selectedFolder.name,
                                selected = true,
                                position = Offset(100f + extraPosition, 100f + extraPosition)
                            )
                            windows = windows + newWindow
                        }


                    }
                )
            }
            WindowsBarMenuScreen(showWindowsMenu)
            windows.filter { !it.minimized }.forEach { window ->
                key(window.id) {
                    DraggableWindow(
                        windowModel = window, onMove = { offSet ->
                            windows = windows.map {
                                if (it.id == window.id) it.copy(position = offSet) else it
                            }
                        },
                        onClose = {
                            windows = windows.filter { it.id != window.id }
                        },
                        onMinimize = {
                            windows = windows.map {
                                if (it.id == window.id) it.copy(minimized = true) else it
                            }
                        },
                        onExpand = {
                            windows = windows.map {
                                if (it.id == window.id) it.copy(expanded = !it.expanded) else it
                            }
                        }
                    )
                }
            }
        }
        WindowsBar(windows = windows, onClickMinimizedWindow = { window ->
            windows = windows.map { if (window.id == it.id) it.copy(minimized = !it.minimized) else it}
        }) { showWindowsMenu = !showWindowsMenu }
    }
}

fun clearFolders(folders: List<FolderModel>): List<FolderModel> {
    val clearFolders = folders.map { it.copy(selected = false) }
    return clearFolders
}
