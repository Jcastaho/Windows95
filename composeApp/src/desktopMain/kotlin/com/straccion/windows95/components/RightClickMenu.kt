package com.straccion.windows95.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandIn
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.straccion.windows95.components.rightmenu.MenuDivider
import com.straccion.windows95.components.rightmenu.MenuItem
import com.straccion.windows95.components.rightmenu.SubMenu
import com.straccion.windows95.model.FolderSortType
import com.straccion.windows95.model.FolderSortType.*
import com.straccion.windows95.model.SubMenuItem

@Composable
fun RightClickMenu(
    showMenu: Boolean,
    position: IntOffset,
    onDismissRequest: () -> Unit,
    createNewFolder: (IntOffset) -> Unit,
    sortFolders:(FolderSortType) -> Unit
) {
    var subMenuPosition: IntOffset? by remember { mutableStateOf(null) }
    var subMenuItems: List<SubMenuItem> by remember { mutableStateOf(emptyList()) }

    LaunchedEffect(position) {
        subMenuPosition = null
    }

    AnimatedVisibility(showMenu, exit = ExitTransition.None, enter = expandIn()) {
        Popup(offset = position, onDismissRequest = { onDismissRequest() }, alignment = Alignment.TopStart) {
            BackgroundComponent(Modifier.width(170.dp)) {
                Column(modifier = Modifier.padding(3.dp)) {
                    MenuItem(text = "Arrange Icons", showSubMenu = true, hovered = {
                        subMenuItems = listOf(
                            SubMenuItem("By Name", onClick = {sortFolders(ByName)}),
                            SubMenuItem("By Size", onClick = {}, enabled = false),
                            SubMenuItem("By Type", onClick = {}, enabled = false),
                            SubMenuItem("By Date", onClick = {sortFolders(ByDate)}),
                        )
                        subMenuPosition = IntOffset(position.x + 165, position.y)
                    })
                    MenuItem(text = "Line up Icons", hovered = { subMenuPosition = null })
                    MenuDivider()
                    MenuItem(
                        text = "Paste", enable = false, hovered = { subMenuPosition = null })
                    MenuItem(
                        text = "Paste Shortcut", enable = false, hovered = { subMenuPosition = null })
                    MenuItem(text = "Undo Copy", hovered = { subMenuPosition = null })
                    MenuDivider()
                    MenuItem(
                        text = "New", showSubMenu = true, hovered = {
                            subMenuItems = listOf(
                                SubMenuItem("Folder", onClick = { createNewFolder(position) }),
                                SubMenuItem("Shortcut", onClick = {}, enabled = false),
                                SubMenuItem("Text Document", onClick = {}, enabled = false),
                                SubMenuItem("Bitmap Image", onClick = {}, enabled = false),
                            )
                            val extraY: Int = it?.y?.toInt() ?: 0
                            subMenuPosition = IntOffset(position.x + 165, position.y + extraY)
                        })
                    MenuDivider()
                    MenuItem(text = "Properties", onClick = {
                        onDismissRequest()
                    }, hovered = { subMenuPosition = null })
                }
            }
        }
        subMenuPosition?.let {
            Popup(
                offset = it, onDismissRequest = { onDismissRequest() }, alignment = Alignment.TopStart
            ) {
                SubMenu(subMenuItems)
            }

        }
    }

}