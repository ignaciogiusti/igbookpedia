package com.plcoding.bookpedia.book.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//Similar a Dto, para almacenamiento local
@Entity
data class BookEntity (
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String,
    val languages: List<String>,
    val authors: List<String>,
    val firstPublishYear: Int?,
    val averageRating: Double?,
    val ratingsCount: Int?,
    val numPageMedian: Int?,
    val numEditions: Int?
)