package com.example.test.data


class DbRepositoryImpl (private val dataBase: MyDatabase) {
    suspend fun getColumnNames() = dataBase.getColumnNames()
    suspend fun getInfoByOneField(
        fields: List<String>,
        param: String,
        arg: String
    ) = dataBase.getInfoByOneField(fields, param, arg)
}