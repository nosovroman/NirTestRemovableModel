package com.example.test.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.common.Constants.CORNER_SIZE
import com.example.test.domain.model.Message

@Composable
fun MessageComponent(
    message: Message,
    color: Color,
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = RoundedCornerShape(CORNER_SIZE)
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = color,
        content = {
            Text(
                text = buildAnnotatedString {
                    append(message.message.joinToString(separator = "\n"))
                    if (message.time != null) {
                        withStyle(
                            style = ParagraphStyle(textAlign = TextAlign.End),
                        ) {
                            withStyle(
                                style = SpanStyle(fontSize = 12.sp)
                            ) {
                                append(String.format("%.1f", message.time/1000.0f) + " сек")
                            }
                        }
                    }
                },
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }
    )
}