package com.straccion.windows95.helper

import androidx.compose.ui.geometry.Offset
import com.straccion.windows95.model.FolderModel
import windows95.composeapp.generated.resources.*
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_explorer
import windows95.composeapp.generated.resources.ic_my_computer
import windows95.composeapp.generated.resources.ic_network

object DefaultFoldersProvider {
    val default = listOf(
        FolderModel(0, "My Computer", Offset(x = 10f, y = 0f), icon = Res.drawable.ic_my_computer),
        FolderModel(1, "Neitwork Neighborhood", Offset(x = 10f, y = 80f), icon = Res.drawable.ic_network),
        FolderModel(2, "Internet Explorer", Offset(x = 10f, y = 180f), icon = Res.drawable.ic_ie),
        FolderModel(3, "Age of Empires II", Offset(x = 10f, y = 280f), icon = Res.drawable.ic_aoe),
        FolderModel(4, "Recycle Bin", Offset(x = 1100f, y = 780f), icon = Res.drawable.ic_bin),
    )
}