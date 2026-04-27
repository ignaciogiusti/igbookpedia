package com.plcoding.bookpedia.book.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDbConstructor: RoomDatabaseConstructor<FavBookDb> {
    override fun initialize(): FavBookDb
}