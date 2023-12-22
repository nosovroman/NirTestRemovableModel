package com.example.test.data

import android.content.Context
import android.content.ContextWrapper
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.test.common.Constants.FILE_NOT_EXIST
import com.example.test.common.Constants.FILE_PATH
import java.io.File


class DatabaseContext(context: Context) : ContextWrapper(context) {
    init {
        Log.w("getDatabasePath DatabaseContext", "init DatabaseContext")
    }

    override fun getDatabasePath(name: String): File {
        Log.w("getDatabasePath", "run getDatabasePath")
        val dbFilePath = FILE_PATH
        Log.d("getDatabasePath Path to file", dbFilePath)
        val result = File(dbFilePath)
        throwFileNotExist(result)

        if (result.parentFile?.exists() == false) {
            result.parentFile?.mkdirs()
        }
        return result
    }

    override fun openOrCreateDatabase(
        name: String,
        mode: Int,
        factory: CursorFactory?,
        errorHandler: DatabaseErrorHandler?
    ): SQLiteDatabase {
        Log.w("getDatabasePath openOrCreateDatabase", "run openOrCreateDatabase")
        var nameOrPath = name
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.P) {
            nameOrPath = FILE_PATH
            throwFileNotExist(File(nameOrPath))
        }
        return openOrCreateDatabase(nameOrPath, mode, factory)
    }

    private fun throwFileNotExist(file: File) {
        if (!file.exists()) {
            Log.e("getDatabasePath file.exists()", "No, doesn't exist: " + file.absolutePath)
            throw SQLiteException(FILE_NOT_EXIST)
        } else {
            Log.w("getDatabasePath file.exists()", "YES, exists: " + file.absolutePath)
        }
    }
}