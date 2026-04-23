package com.plcoding.bookpedia.app

import com.plcoding.bookpedia.book.domain.Book
import kotlinx.serialization.Serializable

interface Route {

    @Serializable
    data object BookGraph: Route

    @Serializable
    data object BookList: Route

    @Serializable
    data class BookDetail(val id: String): Route
}