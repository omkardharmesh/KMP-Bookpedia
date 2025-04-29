package com.plcoding.bookpedia.book.presentation

import androidx.lifecycle.ViewModel
import com.plcoding.bookpedia.book.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/*Shared VM between book-list and book detail screen since passing
* of Book object in nav args is not recommended
* This VM is tied to BookGraph nav graph so even if a user navigates to details screen
* from list screen, the vm will be alive
* Just USE THIS FOR SHARED STATES ONLY*/


class SelectedBookViewModel : ViewModel() {
    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    fun onSelectedBook(selectedBook: Book?) {
        _selectedBook.value = selectedBook
    }

}