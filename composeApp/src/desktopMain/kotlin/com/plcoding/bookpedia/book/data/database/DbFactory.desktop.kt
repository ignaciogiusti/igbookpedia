package com.plcoding.bookpedia.book.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DbFactory {
    actual fun create(): RoomDatabase.Builder<FavBookDb> {
//        Chequea cual es el sistema operativo
        val os = System.getProperty("os.name").lowercase()
//        Chequea donde se almacenaran datos
        val userHome = System.getProperty("user.home")
//        Chequea
//        val appDataDirectory = File(System.getProperty("user.dir"))
        val appDirectory = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "Bookpedia")
            os.contains("mac") -> File(userHome,"Library/Application Support/Bookpedia")
            else -> File(userHome, "./local/share/BookPedia")
        }

//        Si no existe directorio, lo crea. Inicialmente no deberia existir
        if(!appDirectory.exists()) {
            appDirectory.mkdirs()
        }

        val dbFile = File(appDirectory, FavBookDb.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}