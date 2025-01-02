package com.straccion.windows95.model

import androidx.compose.ui.geometry.Offset

data class FolderModel(
    val id:Int,
    val name: String = "New Folder",
    val position: Offset,
    val selected: Boolean = false
)
