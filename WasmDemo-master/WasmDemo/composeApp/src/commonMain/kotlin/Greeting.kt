import models.BookCategory
import models.Books

class Greeting {
    fun greet(): String {
        return "WELCOME TO THE ELISA BOOK\n" +
                "The most popular Finnish audio and e-book service!"
    }

    fun getBooks(): List<Books> {
        val bookList = listOf(
            Books("Simple Way of Piece life", "Simple Way of Piece life", "Armor Ramsey"),
            Books("Simple Way of Piece life", "Simple Way of Piece life", "Armor Ramsey"),
            Books("Simple Way of Piece life", "Simple Way of Piece life", "Armor Ramsey"),
            Books("Simple Way of Piece life", "Simple Way of Piece life", "Armor Ramsey"),
            Books("Simple Way of Piece life", "Simple Way of Piece life", "Armor Ramsey"),
            Books("Simple Way of Piece life", "Simple Way of Piece life", "Armor Ramsey"),
            Books("Simple Way of Piece life", "Simple Way of Piece life", "Armor Ramsey"),
            Books("Simple Way of Piece life", "Simple Way of Piece life", "Armor Ramsey"),
            Books("Simple Way of Piece life", "Simple Way of Piece life", "Armor Ramsey"),
            // Add more items as needed
        )
        return bookList
    }

    fun getBookCategories(): List<BookCategory> {
        val categoryList = listOf(
            BookCategory(1, "OUR TOP-SELLING COLLECTION", "Detective Books", getBooks(), "View all Detective books ->"),
            BookCategory(
                2,
                "OUR MOST-LOVED COLLECTION",
                "Educational Books",
                getBooks(),
                "View all Educational Books ->"
            ),
        )
        return categoryList
    }
}