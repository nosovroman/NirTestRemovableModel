package com.example.test.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.test.common.Constants.BORDER_WIDTH
import com.example.test.common.Constants.CORNER_SIZE_ACTIVE

@Preview
@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    currentValue: String = "",
    enabled: Boolean = true,
    onValueChange: (newValue: String) -> Unit = {},
    trailingIcon: @Composable (enabled: Boolean) -> Unit = {},
    placeholder: String = "",
    maxLines: Int = 3,
    shape: CornerBasedShape = RoundedCornerShape(CORNER_SIZE_ACTIVE),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.background,
        ),
        modifier = modifier
//            .border(
//                width = BORDER_WIDTH,
//                color = MaterialTheme.colors.primary,
//                shape = shape
//            )
            .fillMaxWidth(),
        value = currentValue,
        onValueChange = { onValueChange(it) },
        trailingIcon = { trailingIcon(enabled) },
        shape = shape,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        enabled = enabled,
        maxLines = maxLines,
        textStyle = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = 20.sp),
        placeholder = { Text(text = placeholder) }
    )
}