package com.example.test.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.common.Constants
import com.example.test.common.Constants.CORNER_SIZE_ACTIVE
import com.example.test.common.Constants.SPACER_SIZE
import com.example.test.presentation.ui.theme.BackgroundColor

@Preview
@Composable
fun DropdownMenuComponent(
    chosenItemTitle: String = "",
    expanded: Boolean = true,
    onMenuClick: () -> Unit = {},
    items: List<String> = listOf("item"),
    onItemClick: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = chosenItemTitle,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onMenuClick()
                }
                .padding(CORNER_SIZE_ACTIVE)
        )
        DropdownMenu(
            modifier = Modifier
                .background(color = BackgroundColor)
                .padding(start = SPACER_SIZE, bottom = SPACER_SIZE)
                .border(
                    width = Constants.BORDER_WIDTH,
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(Constants.CORNER_SIZE_ACTIVE)
                ),
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            content = {
                items.forEach {
                    DropdownMenuItem(
                        onClick = {
                            onItemClick(it.toString())
                        },
                        content = {
                            Text(
                                text = it.toString(),
                                color = MaterialTheme.colors.primary
                            )
                        }
                    )
                }
            }
        )
    }
}