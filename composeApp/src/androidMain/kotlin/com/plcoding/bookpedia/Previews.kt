package com.plcoding.bookpedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListState
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.presentation.book_list.components.BookSearchBar
import kotlin.collections.emptyList

//@Preview
//@Composable
//private fun BookSearchBarPreview() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.LightGray)
//    ) {
//            BookSearchBar(
//                searchQuery = "",
//                onSearchQueryChange = {},
//                onImeSearch = {},
//                modifier = Modifier
//                    .fillMaxWidth()
//            )
//        }
//    }

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        authors = listOf("Author $it"),
        imageUrl = "https://picsum.photos/200/300?random=$it",
        description = "Description $it",
        averageRating = 4.5,
        ratingsCount = 100,
        firstPublishYear = 2000,
        languages = emptyList(),
        numPages = 200,
        numEditions = 1
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen (
        state = BookListState(
            searchResult = books
        ),
            onAction = {}
    )
}