package com.example.test.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun DividerComponent() {
    Box(
        modifier = Modifier
            //.padding(horizontal = PADDING_SCREEN_HORIZONTAL)
            .fillMaxWidth()
            .height(1.dp)
            .background(
                Brush.horizontalGradient(
                colors = listOf(MaterialTheme.colors.background, MaterialTheme.colors.primary, MaterialTheme.colors.background)
            )),
        content = {}
    )
}