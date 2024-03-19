package models

data class BookCategory(val id: Int, val categoryDesc: String, val categoryName: String, val books: List<Books>, val viewAllText: String)