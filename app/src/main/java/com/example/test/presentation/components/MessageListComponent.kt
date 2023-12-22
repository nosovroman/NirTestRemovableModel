package com.example.test.presentation.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.test.common.Constants.PADDING_BOTTOM_MSG_LIST
import com.example.test.domain.model.Message
import com.example.test.presentation.DbViewModel
import kotlinx.coroutines.coroutineScope

@Composable
fun MessageListComponent(
    messageList: List<Message>,
    modifier: Modifier = Modifier,
    modifierItem: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    LaunchedEffect(messageList.size) {
        coroutineScope {
            scrollState.animateScrollToItem(messageList.lastIndex)
        }
    }
    LazyColumn(
        state = scrollState,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = PADDING_BOTTOM_MSG_LIST)
            //.verticalScroll(scrollState)
            //.weight(weight = 1f, fill = false)
            //.border(1.dp, color = Color.Blue)
        ,
        content = {
            items(messageList) { message ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (message.fromApp) Arrangement.Start else Arrangement.End,
                    content = {
                        MessageComponent(
                            message = message,
                            color = if (message.fromApp) MaterialTheme.colors.surface else MaterialTheme.colors.primarySurface,
                            modifier = if (message.fromApp) modifierItem.padding(end = 40.dp) else modifierItem.padding(start = 40.dp)
                        )
                    }
                )

                Spacer(modifier = Modifier.size(5.dp))
            }
        }
    )
}