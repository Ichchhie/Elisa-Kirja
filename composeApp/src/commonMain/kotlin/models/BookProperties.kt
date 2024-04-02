package models

// Author
data class Author(
    val id: String,
    val sortName: String,
    val path: String,
    val role: String,
    val title: String
)

//Categories
data class BookCategory(
    val id: Int,
    val categoryDesc: String,
    val categoryName: String,
    val books: List<Books>,
    val viewAllText: String
)