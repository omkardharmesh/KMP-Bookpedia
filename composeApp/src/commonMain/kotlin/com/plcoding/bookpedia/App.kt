package com.plcoding.bookpedia

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.book.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailAction
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailScreenRoot
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailViewModel
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.book.presentation.navigation.bookNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    /*MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ) {
                composable<Route.BookList>(
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
                                Route.BookDetail(
                                    id = book.id
                                )
                            )
                        }
                    )
                }

                composable<Route.BookDetail>(
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
    }*/
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.BookGraph
        ) {
            bookNavigation(
                navController = navController
            )
        }
    }

}

/*To get shared viewmodel from parent back stack entry's scope*/
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}