
import models.BookCategory
import models.Books

class Strings {
    fun greet(): String {
        return "Welcome to\n \n" +"Elisa Kirja"
    }
    fun browseBooks(): String {
        return "Browse Audio & E-books"
    }
    fun elisaDescription(): String {
        return "The most popular Finnish audio and e-book service." +
                " Something to listen to and read for every taste, discover something new every day." +
                " Enjoy your favorite books anywhere and anytime. Even without an internet connection." +
                "We offer you 30 hours of listening/reading time, which you can use for books you like."
    }
    fun getBooks(): List<Books> {
        val bookList = listOf(
            //Books(1, "Simple Way of Piece life", authors = listOf()),
            Books("204131", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("207653", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("200674", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("204844", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("199704", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("204071", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("201683", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("201105", "Simple Way of Piece life", "Armor Ramsey", ""),
            Books("204161", "Simple Way of Piece life", "Armor Ramsey", "")
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
                "View all Detective books"
            ),
            BookCategory(
                2,
                "OUR MOST-LOVED COLLECTION",
                "Educational Books",
                getBooks(),
                "View all Educational Books"
            ),
            BookCategory(
                3,
                "Third TOP-SELLING COLLECTION",
                "Detective Books",
                getBooks(),
                "View all Detective books"
            ),
            BookCategory(
                4,
                "Forth MOST-LOVED COLLECTION",
                "Educational Books",
                getBooks(),
                "View all Educational Books"
            ),
            BookCategory(
                5,
                "fifth TOP-SELLING COLLECTION",
                "Detective Books",
                getBooks(),
                "View all Detective books"
            ),
            BookCategory(
                6,
                "Sixth MOST-LOVED COLLECTION",
                "Educational Books",
                getBooks(),
                "View all Educational Books"
            ),
        )
        return categoryList
    }
}