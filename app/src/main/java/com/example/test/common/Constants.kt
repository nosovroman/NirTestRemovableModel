package com.example.test.common

import androidx.compose.ui.unit.dp

object Constants {
    const val FLASH_PATH = "/storage/8884-A9DF"     // черная
    //const val FLASH_PATH = "/storage/emulated/0/04D6-AD11" // в памяти телефона
    //const val FILE_PATH = "/storage/emulated/0/0DDD/test.db" // в памяти телефона
    //const val FILE_PATH = "/storage/9438-00B8/test.db"    // кингстон
    //const val FLASH_PATH = "/storage/14E7-1C43"        // димина 14E7-1C43
    //const val FLASH_PATH = "/storage/F6F3-EC9F"        // 256 Гб
    //const val DB_NAME = "test.db"
    //const val TABLE_NAME = "DbName"
    const val DB_NAME = "DBLeaks.db"
    const val TABLE_NAME = "VkINF"
    const val FILE_PATH = "$FLASH_PATH/$DB_NAME"

    const val DB_VERSION = 1
    const val FILE_NOT_EXIST = "FILE DOESN'T EXIST"

    val TOOLBAR_HEIGHT = 48.dp
    val PADDING_BOTTOM_MSG_LIST = 122.dp
    const val TOOLBAR_NAME = "RevengEye"
    val CORNER_SIZE = 20.dp
    val CORNER_SIZE_ACTIVE = 15.dp
    val PADDING_SCREEN_HORIZONTAL = 15.dp
    val SPACER_SIZE = 10.dp
    val BORDER_WIDTH = 1.dp
    const val PARAMETER = "Параметр: "

    const val EMOJI = "\uD83D\uDC3A"
}