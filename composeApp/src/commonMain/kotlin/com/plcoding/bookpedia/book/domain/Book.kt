package com.plcoding.bookpedia.book.domain

data class Book(
    val id: String,
    val title: String,
    val authors: List<String>,
    val imageUrl: String,
    val description: String?,
    val languages: List<String>,
    val averageRating: Double?,
    val firstPublishYear: Int?,
    val ratingsCount: Int?,
    val numPages: Int?,
    val numEditions: Int?
)
