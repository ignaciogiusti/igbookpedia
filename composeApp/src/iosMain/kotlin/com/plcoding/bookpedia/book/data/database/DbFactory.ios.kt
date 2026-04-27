//@file:OptIn(ExperimentalForeignApi::class)
package com.plcoding.bookpedia.book.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSUserDomainMask
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager

actual class DbFactory {
    actual fun create(): RoomDatabase.Builder<FavBookDb> {
        val dbFile = documentDirectory() + "/${FavBookDb.DB_NAME}"
        return Room.databaseBuilder<FavBookDb>(
            name = dbFile,
        )
    }

    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager().URLForDirector(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropiateForURL = null,
            create = false,
            error = null
        )
        return requireNotNull(documentDirectory?.path())
        }
}