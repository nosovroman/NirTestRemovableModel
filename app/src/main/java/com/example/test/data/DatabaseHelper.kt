package com.example.test.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import android.database.sqlite.SQLiteOpenHelper
import com.example.test.common.Constants.DB_VERSION


class DatabaseHelper(context: Context, name: String) :
    SQLiteOpenHelper(DatabaseContext(context), name, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}