package com.plcoding.bookpedia.core.presentation

import androidx.compose.ui.graphics.Color

val DarkOrange = Color(0xFF8C00)
val DarkRed = Color(0xFF8B0000)
val DarkBlue = Color(0xFF0B405E)

val DarkGreen = Color(0xFF006400)
val DesertWhite = Color(0xFFF7F7F7)
val SandYellow = Color(0xFFFFBD64)
val LightBlue = Color(0xFF9AD9FF)
val Turquoise = Color(0x40E0D0)
val TurquoiseBlue = Color(0xFF8C8C8C)
val Cyan = Color(0xFF00FFFF)

//expect val FlavorBg: Color

// 1. Remove 'expect'.
// 2. Change to 'var' so Android can overwrite it.
// 3. Provide the default value (iOS and Desktop will use this).
var FlavorBg: Color = DarkRed.copy(alpha = 0.9f)
    get() = field
    set(value) { field = value }