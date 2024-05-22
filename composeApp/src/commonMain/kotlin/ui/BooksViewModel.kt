package ui

import API.GetBooks
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import models.Books

class BooksViewModel {
    var books by mutableStateOf<List<Books>?>(null)
        private set
    var isLoading by mutableStateOf(true)
        private set

    fun loadBooks() {
        // Use a default CoroutineScope for this ViewModel
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            isLoading = true
            val bookApi = GetBooks()
            val response = bookApi.retrieveAllBooksOfCategory(null)
            books = response?.record?.books
            if (books.isNullOrEmpty()) {
                delay(1000) // Add a small delay if books are not loaded immediately
            }
            delay(1000)
            isLoading = false
        }
    }
}