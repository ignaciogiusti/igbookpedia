package com.plcoding.bookpedia.book.data.database

import androidx.room.RoomDatabase

expect class DbFactory {
    fun create(): RoomDatabase.Builder<FavBookDb>
}