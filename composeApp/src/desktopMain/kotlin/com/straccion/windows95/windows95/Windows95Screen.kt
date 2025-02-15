package com.straccion.windows95.windows95

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import com.straccion.windows95.components.DraggableFolder
import com.straccion.windows95.components.DraggableWindow
import com.straccion.windows95.components.RightClickMenu
import com.straccion.windows95.components.windowbarmenu.WindowsBar
import com.straccion.windows95.components.windowbarmenu.WindowsBarMenuScreen
import com.straccion.windows95.extensions.clickableWithoutRipple
import com.straccion.windows95.extensions.onrightClic
import com.straccion.windows95.helper.DefaultFoldersProvider
import com.straccion.windows95.model.FolderModel
import com.straccion.windows95.model.FolderSortType
import com.straccion.windows95.model.WindowModel

@Composable
fun Windows95Screen() {
    var showWindowsMenu by remember { mutableStateOf(false) }
    var folders by remember { mutableStateOf(DefaultFoldersProvider.default) }
    var windows by remember { mutableStateOf(listOf<WindowModel>()) }

    var showRightClickMenu by remember { mutableStateOf(false) }
    var rightClickPosition by remember { mutableStateOf(IntOffset.Zero) }


    Column {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f)
                .clickableWithoutRipple {
                    showWindowsMenu = false
                    showRightClickMenu = false
                    folders = clearFolders(folders)
                }
                .onrightClic {
                    rightClickPosition = it
                    showRightClickMenu = true

                }
        ) {
            folders.forEach { folder ->
                DraggableFolder(
                    folder, onMove = { newPosition ->
                        folders = folders.map {
                            if (it.id == folder.id) it.copy(position = newPosition) else it
                        }
                    }, onTapFolder = { id ->
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
            RightClickMenu(
                showMenu = showRightClickMenu,
                position = rightClickPosition,
                onDismissRequest = { showRightClickMenu = false },
                createNewFolder = {
                    val newFolder =
                        FolderModel(id = folders.size + 1, position = Offset(it.x.toFloat(), it.y.toFloat()))
                    folders = folders + newFolder
                    showRightClickMenu = false
                },
                sortFolders = {sortType ->
                    showWindowsMenu = false
                    folders = when(sortType){
                        FolderSortType.ByName -> folders.sortedBy { it.name }
                        FolderSortType.ByDate -> folders.sortedByDescending { it.createdDate }
                    }.mapIndexed { pos: Int, folder: FolderModel ->
                        folder.copy(
                            position = Offset(
                                x = 0f,
                                y = (pos * 75).toFloat()
                            )
                        )
                    }
                }
            )
        }
        WindowsBar(windows = windows, onClickMinimizedWindow = { window ->
            windows = windows.map { if (window.id == it.id) it.copy(minimized = !it.minimized) else it }
        }) { showWindowsMenu = !showWindowsMenu }
    }
}

fun clearFolders(folders: List<FolderModel>): List<FolderModel> {
    val clearFolders = folders.map { it.copy(selected = false) }
    return clearFolders
}
