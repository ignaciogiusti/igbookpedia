package com.plcoding.bookpedia.core.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

val FlavorBg: Color = DarkOrange.copy(0.7f)

@Composable
fun FlavorText(modifier: Modifier = Modifier) {
    Text("Free")
}