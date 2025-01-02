package com.straccion.windows95.components.windowbarmenu

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.straccion.windows95.components.BackgroundComponent
import com.straccion.windows95.components.rightmenu.MenuDivider
import com.straccion.windows95.model.WindowsMenuCategory
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.*
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_exchange
import windows95.composeapp.generated.resources.ic_msdos
import windows95.composeapp.generated.resources.ic_programs

@Composable
fun WindowsBarMenuScreen(showWindowsMenu: Boolean) {

    var subBarMenuPosition by remember { mutableStateOf<Float?>(null) }
    var subBarMenuCategory by remember { mutableStateOf<WindowsMenuCategory?>(null) }

    if (showWindowsMenu) {
        Column {
            Spacer(Modifier.weight(1f))
            Row {
                WindwsMenu {pos, category ->
                    subBarMenuCategory = category
                    subBarMenuPosition = pos
                }
                subBarMenuPosition?.let {
                    BackgroundComponent(modifier = Modifier
                        .offset(y = it.dp)
                        .width(190.dp)){
                        Column{
                            when(subBarMenuCategory){
                                WindowsMenuCategory.Programs -> {
                                    WindowsMenuItem(
                                        text = "Accesories",
                                        painter = painterResource(Res.drawable.ic_programs),
                                        isSubMenu = true
                                    ){}
                                    WindowsMenuItem(
                                        text = "StartUp",
                                        painter = painterResource(Res.drawable.ic_programs),
                                        isSubMenu = true
                                    ){}
                                    WindowsMenuItem(
                                        text = "Microsoft Exchange",
                                        painter = painterResource(Res.drawable.ic_exchange),
                                        isSubMenu = true
                                    ){}
                                    WindowsMenuItem(
                                        text = "MS-DOS prompt",
                                        painter = painterResource(Res.drawable.ic_msdos),
                                        isSubMenu = true
                                    ){}
                                    WindowsMenuItem(
                                        text = "Windows Explorer",
                                        painter = painterResource(Res.drawable.ic_explorer),
                                        isSubMenu = true
                                    ){}
                                }
                                WindowsMenuCategory.Documents -> {
                                    WindowsMenuItem(
                                        text = "My Documents",
                                        painter = painterResource(Res.drawable.ic_documents),
                                        isSubMenu = true
                                    ){}
                                    MenuDivider()
                                    WindowsMenuItem(
                                        text = "Readme",
                                        painter = painterResource(Res.drawable.ic_notepad),
                                        isSubMenu = true
                                    ){}
                                }
                                WindowsMenuCategory.Settings -> {
                                    WindowsMenuItem(
                                        text = "Control Panel",
                                        painter = painterResource(Res.drawable.ic_control),
                                        isSubMenu = true
                                    ){}
                                    WindowsMenuItem(
                                        text = "Printers",
                                        painter = painterResource(Res.drawable.ic_printer),
                                        isSubMenu = true
                                    ){}
                                    WindowsMenuItem(
                                        text = "Folder options...",
                                        painter = painterResource(Res.drawable.ic_folder_options),
                                        isSubMenu = true
                                    ){}
                                }
                                WindowsMenuCategory.Find -> {
                                    WindowsMenuItem(
                                        text = "Files or Folders...",
                                        painter = painterResource(Res.drawable.ic_find_document),
                                        isSubMenu = true
                                    ){}
                                    WindowsMenuItem(
                                        text = "Computer...",
                                        painter = painterResource(Res.drawable.ic_find_computer),
                                        isSubMenu = true
                                    ){}
                                    WindowsMenuItem(
                                        text = "On the Internet...",
                                        painter = painterResource(Res.drawable.ic_find_internet),
                                        isSubMenu = true
                                    ){}
                                    WindowsMenuItem(
                                        text = "People",
                                        painter = painterResource(Res.drawable.ic_find_people),
                                        isSubMenu = true
                                    ){}
                                }
                                null -> {}
                            }
                        }
                    }

                }
            }

        }
    }
}