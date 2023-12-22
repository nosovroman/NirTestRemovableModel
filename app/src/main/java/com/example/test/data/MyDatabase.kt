package com.example.test.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.test.common.Constants.DB_NAME
import com.example.test.common.Constants.TABLE_NAME
import kotlinx.coroutines.*

class MyDatabase(private val context: Context) {
    private var databaseHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase

    init {
        databaseHelper = DatabaseHelper(context, DB_NAME)
        database = databaseHelper.readableDatabase
    }

    suspend fun getColumnNames(): List<String> {
        return withContext(Dispatchers.IO) {
            val columnNames = mutableListOf<String>()
            val cursor = database.rawQuery("PRAGMA table_info($TABLE_NAME)", null)
            cursor.use {
                while (cursor.moveToNext()) {
                    val colIdx = cursor.getColumnIndex("name")
                    if (colIdx < 0) break else columnNames.add(cursor.getString(colIdx))
                }
            }
            columnNames
        }
    }

    suspend fun getInfoByOneField(
        fields: List<String>,
        searchParam: String,
        searchArg: String
    ): List<String> {
        return withContext(Dispatchers.IO) {
            val infoList = mutableListOf<String>()
            val query = "SELECT * FROM $TABLE_NAME WHERE ${searchParam} = \"${searchArg}\""
            val cursor: Cursor = database.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                do {
                    val currentInfo = buildString {
                        for (field in fields.toMutableList().apply { remove(searchParam) }) {
                            append("${field}: ${cursor.getString(cursor.getColumnIndexOrThrow(field))} \n")
                        }
                    }.trim()
                    infoList.add(currentInfo)
                } while (cursor.moveToNext())
            }
            cursor.close()
            infoList
        }
    }
//            val fields = mutableMapOf("full_name" to "", "id_vk" to vkId, "phone" to phone)
//            lateinit var searchParam: Pair<String, String>  // field_name, search_value
//            for ((key, value) in fields) {
//                if (value.isNotBlank()) {
//                    searchParam = Pair(key, value)
//                    fields.remove(key)
//                    break
//                }
//            }
//    suspend fun getInfoByOneField(vkId: String, phone: String): List<String> {
//        return withContext(Dispatchers.IO) {
//            val fields = mutableMapOf("full_name" to "", "id_vk" to vkId, "phone" to phone)
//            lateinit var searchParam: Pair<String, String>  // field_name, search_value
//            for ((key, value) in fields) {
//                if (value.isNotBlank()) {
//                    searchParam = Pair(key, value)
//                    fields.remove(key)
//                    break
//                }
//            }
//
//            val infoList = mutableListOf<String>()
//            val query = "SELECT * FROM $TABLE_NAME WHERE ${searchParam.first} = \"${searchParam.second}\""
//            val cursor: Cursor = database.rawQuery(query, null)
//            if (cursor.moveToFirst()) {
//                do {
//                    val currentInfo = buildString {
//                        for (field in fields) {
//                            append("${field.key}: ${cursor.getString(cursor.getColumnIndexOrThrow(field.key))} \n")
//                        }
//                    }.trim()
//                    infoList.add(currentInfo)
//                } while (cursor.moveToNext())
//            }
//            cursor.close()
//            Log.d("gettingMethod", infoList.toString())
//            infoList
//        }
//    }
}