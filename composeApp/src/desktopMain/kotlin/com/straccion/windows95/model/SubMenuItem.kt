package com.straccion.windows95.model

data class SubMenuItem(
    val title: String,
    val enabled:Boolean = true,
    val onClick:() -> Unit
)
