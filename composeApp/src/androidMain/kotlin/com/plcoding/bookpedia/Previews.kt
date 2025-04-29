package com.plcoding.bookpedia

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListState
import com.plcoding.bookpedia.book.presentation.book_list.components.BookListItem
import com.plcoding.bookpedia.book.presentation.book_list.components.BookSearchBar

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
private fun BookSearchBarPreview() {
    MaterialTheme {
        BookSearchBar(
            searchQuery = "Kotlin",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}


private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://ndvndjvkdnvbkjdsapicsum.photos/200",
        authors = listOf("Kotlin, koin"),
        description = "Book $it",
        languages = listOf("English", "Korean"),
        averageRating = 4.5,
        firstPublishYear = null,
        ratingCount = 100,
        numPages = null,
        numEditions = 3,
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen(
        state = BookListState(
            searchResults = books
        ),
        onAction = {}
    )
}

@Preview(showBackground = true)
@Composable
fun BookListItemPreview() {
    MaterialTheme {
        BookListItem(
            book = Book(
                id = "1234",
                title = "Kotlin Programming: The Big Nerd Ranch Guide",
                imageUrl = "https://example.com/book.jpg",
                authors = listOf("Josh Skeen", "David Greenhalgh"),
                description = "Kotlin is a statically typed programming language designed to interoperate with Java and run on the Java Virtual Machine.",
                languages = listOf("English"),
                firstPublishYear = "2018",
                averageRating = 4.5,
                ratingCount = 120,
                numPages = 480,
                numEditions = 2
            ),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}