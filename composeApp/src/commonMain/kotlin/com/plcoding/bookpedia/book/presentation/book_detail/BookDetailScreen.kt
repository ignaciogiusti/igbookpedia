package com.plcoding.bookpedia.book.presentation.book_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.book.presentation.book_detail.components.BlurredImageBg

@Composable
fun BookDetailScreenRoot(
    viewModel: BookDetailViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookDetailScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is BookDetailAction.OnBackClick -> onBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun BookDetailScreen(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit,
) {
    BlurredImageBg(
        imageUrl = state.book?.imageUrl,
        isFavorite = state.isFavorite,
        onBackClick = {
            onAction(BookDetailAction.OnBackClick)
        },
        OnFavoriteClick = {
            onAction(BookDetailAction)
        },
        modifier = Modifier
            .fillMaxSize()
    ) {}
}