package com.plcoding.bookpedia.core.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun flavorBg() {
    FlavorBg = Turquoise.copy(0.7f)
}

@Composable
fun FlavorTest(modifier: Modifier = Modifier) {
    Text("Paid")
}