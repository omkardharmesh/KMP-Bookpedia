package com.plcoding.bookpedia.book.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.app.Route.BookGraph
import com.plcoding.bookpedia.book.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailAction
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailScreenRoot
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailViewModel
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.sharedKoinViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.bookNavigation(
    navController: NavHostController
) {
    navigation<Route.BookGraph>(
        startDestination = BookGraph.BookList
    ) {
        composable<BookGraph.BookList>(
            exitTransition = {
                slideOutHorizontally()
            },
            popEnterTransition = {
                slideInHorizontally()
            }
        ) {
            val viewModel = koinViewModel<BookListViewModel>()
            val selectedBookViewModel =
                it.sharedKoinViewModel<SelectedBookViewModel>(navController)

            //cause everytime book list loads, this needs to be set null
            LaunchedEffect(true) {
                selectedBookViewModel.onSelectedBook(null)
            }

            BookListScreenRoot(
                viewModel = viewModel,
                onBookClick = { book ->
                    selectedBookViewModel.onSelectedBook(book)
                    navController.navigate(
                        BookGraph.BookDetail(
                            id = book.id
                        )
                    )
                }
            )
        }

        composable<BookGraph.BookDetail>(
            enterTransition = {
                slideInHorizontally { initialOffset ->
                    initialOffset
                }
            },
            exitTransition = {
                slideOutHorizontally { initialOffset ->
                    initialOffset
                }
            }
        ) { entry ->
            val selectedBookViewModel =
                entry.sharedKoinViewModel<SelectedBookViewModel>(navController)
            val bookDetailViewModel =
                koinViewModel<BookDetailViewModel>()
            val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

            //val args = entry.toRoute<Route.BookDetail>()
            LaunchedEffect(selectedBook) {
                selectedBook?.let {
                    bookDetailViewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                }
            }

            BookDetailScreenRoot(
                viewModel = bookDetailViewModel,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}