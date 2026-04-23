package com.plcoding.bookpedia

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.compose_multiplatform
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.repository.DefaultBookRepository
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ) {
                composable<Route.BookList> {
                    val viewModel = koinViewModel<BookListViewModel>()
                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = {

                        },
                    )
                }
                composable<Route.BookDetail> { entry ->
                    val args = entry.toRoute<Route.BookDetail>()

//                    Dummy box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Book detail screen. Id ${args.id}")
                    }
                }
            }
        }
    }

}