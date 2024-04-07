import models.BookCategory
import models.Books

class Greeting {
    fun greet(): String {
        return "WELCOME TO THE ELISA BOOK\n" +
                "The most popular Finnish audio and e-book service.\n" +
                "Something to listen to and read for every taste, discover something new every day.\n" +
                "Enjoy your favorite books anywhere and anytime. Even without an internet connection.\n" +
                "We offer you 30 hours of listening/reading time, which you can use for books you like."
    }

    fun getBooks(): List<Books> {
        val bookList = listOf(
            //Books(1, "Simple Way of Piece life", authors = listOf()),
            Books("1", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("1", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("1", "Simple Way of Piece life", "Armor Ramsey", "")
        )
        return bookList
    }

    fun getBookCategories(): List<BookCategory> {
        val categoryList = listOf(
            BookCategory(
                1,
                "OUR TOP-SELLING COLLECTION",
                "Detective Books",
                getBooks(),
                "View all Detective books ->"
            ),
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