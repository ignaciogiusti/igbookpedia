package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Test",
    val searchResult: List<String> = emptyList(),
    val favoriteBooks: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
