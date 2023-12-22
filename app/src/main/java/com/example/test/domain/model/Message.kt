package com.example.test.domain.model

data class Message(
    val fromApp: Boolean = true,
    val message: List<String> = List(35){"Ладно, Пикачу..."},//listOf("Ладно, Пикачу...")
    val time: Long? = null
)

//data class Message(
//    val fromApp: Boolean = true,
//    val message: String = ""
//)

//fun Message.getMessageString(): String {
//    return message.joinToString(separator = "\n")
//}