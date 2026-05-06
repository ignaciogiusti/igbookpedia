package com.plcoding.bookpedia.book.data.database

import android.content.Context
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

actual class DbFactory(
    private val context: Context,
) {
    actual fun create(): RoomDatabase.Builder<FavBookDb> {
        val appContext = context.applicationContext
//        val dbFile = appContext.getDatabasePath() + "/${FavBookDb.DB_NAME}"
        val dbFile = appContext.getDatabasePath(FavBookDb.DB_NAME)
        return databaseBuilder(context = appContext, name = dbFile.absolutePath)
    }
}