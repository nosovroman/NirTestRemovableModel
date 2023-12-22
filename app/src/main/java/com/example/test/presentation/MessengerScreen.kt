package com.example.test.presentation

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.test.common.Constants
import com.example.test.common.Constants.BORDER_WIDTH
import com.example.test.common.Constants.EMOJI
import com.example.test.common.Constants.PADDING_SCREEN_HORIZONTAL
import com.example.test.common.Constants.PARAMETER
import com.example.test.presentation.components.*

@Composable
fun MessengerScreen(
    context: Context,
    viewModel: DbViewModel
) {
    val state = viewModel.infoList.value
    val messages = viewModel.infoMessages
    viewModel.scrollMsgStateInit(rememberLazyListState())

    Scaffold(
        topBar = { ToolbarComponent() },
        content = {
            Column(
                modifier = Modifier
                    .padding(horizontal = PADDING_SCREEN_HORIZONTAL),
                content = {
                    DividerComponent()
                    Spacer(modifier = Modifier.size(1.dp))
                    when (viewModel.storageIsConnectedState) {
                        ConState.NOT_CONNECTED -> {
                            Text(text = "Подключите устройство")
                        }
                        ConState.CONNECTING -> {
                            Text(text = "Подключение...")
                            viewModel.initDb(context)
                            if (viewModel.dbIsInitedState) viewModel.getColumnNames()
                        }
                        ConState.CONNECTED -> {
                            MessageListComponent(messageList = messages)
                        }
                    }
                }
            )
        },
        bottomBar = {
            Column {
                if  (state.isLoading) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        content = {
                            LinearProgressIndicator()
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .border(
                            width = BORDER_WIDTH,
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(Constants.CORNER_SIZE_ACTIVE)
                        ),
                    content = {
                        if (viewModel.hasColumnsState) {
                            DropdownMenuComponent(
                                chosenItemTitle = "$PARAMETER${viewModel.chosenColumnState}",
                                expanded = viewModel.expandedState,
                                onMenuClick = { viewModel.reverseExpanded() },
                                items = viewModel.columnListState.map { "$PARAMETER$it" },
                                onItemClick = {
                                    columnName -> viewModel.setChosenColumn(columnName.substringAfter(PARAMETER))
                                    viewModel.reverseExpanded()
                                },
                                onDismissRequest = { viewModel.reverseExpanded() }
                            )
                            DividerComponent()
                            TextFieldComponent(
                                currentValue = viewModel.vkIdState,
                                onValueChange = { viewModel.setVkId(it) },
                                enabled = viewModel.hasColumnsState && !state.isLoading,
                                //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                trailingIcon = { enabled ->
                                    IconButton(
                                        onClick = {
                                            viewModel.getInfoByOneField(
                                                fields = viewModel.columnListState,
                                                param = viewModel.chosenColumnState,
                                                arg = viewModel.vkIdState,
                                            )
                                        },
                                        enabled = enabled,
                                        content = {
                                            Icon(
                                                imageVector = Icons.Outlined.Send,
                                                contentDescription = EMOJI,
                                                tint = MaterialTheme.colors.primary
                                            )
                                        }
                                    )
                                },
                                placeholder = "Введите значение выбранного параметра"
                            )
                        }
                    }
                )
            }
        }
    )
}


//TextFieldComponent(
//currentValue = viewModel.phoneState,
//onValueChange = { viewModel.setPhone(it) },
//enabled = viewModel.storageIsConnectedState == ConState.CONNECTED && !state.isLoading,
//keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//trailingIcon = { enabled ->
//    IconButton(
//        onClick = {
//            viewModel.getInfoByOneField(phone = viewModel.phoneState)
//        },
//        enabled = enabled,
//        content = {
//            Icon(
//                imageVector = Icons.Outlined.Send,
//                contentDescription = EMOJI,
//                tint = MaterialTheme.colors.primary
//            )
//        }
//    )
//},
//placeholder = "Номер телефона"
//)

//                    if (!viewModel.storageIsConnectedState) {
//                        Text(text = "Подключите устройство")
//                        ButtonComponent(
//                            onClick = {
//                                viewModel.initDbButton(context)
//                            },
//                            text = {
//                                Text(text = "Подключиться к устройству")
//                            }
//                        )
//                    }
//                    if (viewModel.storageIsConnectedState) {
//                        viewModel.initDb(context)
//                        MessageListComponent(messageList = messages)
//                    }