package com.example.test.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.test.common.Constants.TOOLBAR_HEIGHT
import com.example.test.common.Constants.TOOLBAR_NAME
import com.example.test.presentation.ui.theme.ToolbarColor
import com.example.test.presentation.ui.theme.ToolbarContentColor

@Composable
fun ToolbarComponent(title: String = TOOLBAR_NAME) {
    TopAppBar (
        backgroundColor = ToolbarColor,
        contentColor = ToolbarContentColor,
        modifier = Modifier.height(TOOLBAR_HEIGHT),
        content = {
            Text(
                text = title,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    )
}