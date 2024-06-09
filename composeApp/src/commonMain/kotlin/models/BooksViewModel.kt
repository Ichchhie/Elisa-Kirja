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
    private val _user = MutableStateFlow<BookContainer?>(null)
    val user: StateFlow<BookContainer?> get() = _user
    private val apiService= BooksService()

    init {
        Strings().getBookCategories().forEach { category ->
            category.books.forEach {
                it.id?.let { it1 -> fetchData(it1) }
            }
        }
    }
    fun fetchData(userId: String){
        screenModelScope.launch {
            //fetch data code
            _user.value = apiService.retrieveBooksFromAPI(userId)
            _user.value?.let { user ->
                allBooksStateFlow.update { it + user }
            }
        }
    }
    fun isDataLoaded(): Boolean {
        return allBooks.value.isNotEmpty() && allBooks.value.size==Strings().getBooks().size
    }

}