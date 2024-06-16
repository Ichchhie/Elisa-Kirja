package models

import API.BooksService
import Strings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//this viewmodel class is inheriting the screenmodel class of voyager library and is similar to the android viewmodel
class BooksViewModel() : ScreenModel {
    private val allBooksStateFlow = MutableStateFlow<List<BookContainer?>>(emptyList())
    val allBooks: StateFlow<List<BookContainer?>> get() = allBooksStateFlow
    private val _bookStateFlow = MutableStateFlow<BookContainer?>(null)
    private val _newBooksStateFlow = MutableStateFlow<Boolean?>(false)
    val isNewBooksAdded: StateFlow<Boolean?> get() = _newBooksStateFlow
    private val apiService = BooksService()
    private var categories = Strings().getBookCategories()
    private val _endReachedFlow = MutableStateFlow<Boolean?>(false)
    val hasReachedEnd: StateFlow<Boolean?> get() = _endReachedFlow

    init {
        fetchMoreData(0)
    }

    private fun fetchMoreData(currentIndex: Int) {
        println("hello inside fetch more${currentIndex}")
        if (currentIndex < categories.size) {
            val category = categories[currentIndex]
            category.books.forEach {
                it.id?.let { it1 -> fetchData(it1) }
            }
            _newBooksStateFlow.update { true }
        } else {
            _endReachedFlow.update { true }
        }
    }

    private fun fetchData(userId: String) {
        screenModelScope.launch {
            //fetch data code
            _bookStateFlow.value = apiService.retrieveBooksFromAPI(userId)
            _bookStateFlow.value?.let { user ->
                allBooksStateFlow.update { it + user }
            }
        }
    }

    fun isDataLoaded(): Boolean {
        return allBooks.value.isNotEmpty() && allBooks.value.size == Strings().getBooks().size
    }

    fun loadMoreData(currentIndex: Int) {
        fetchMoreData(currentIndex)
    }
}