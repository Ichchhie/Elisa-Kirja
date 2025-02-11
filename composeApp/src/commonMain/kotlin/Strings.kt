
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
            Books("67ab0e79ad19ca34f8ff47e7", "Simple Way of Piece life", listOf(), ""),
            Books("67ab10b9acd3cb34a8ddc762", "Simple Way of Piece life", listOf(), ""),
            Books("67ab1116acd3cb34a8ddc7df", "Simple Way of Piece life", listOf(), ""),
            Books("67ab113eacd3cb34a8ddc821", "Simple Way of Piece life", listOf(), ""),
            Books("67ab119fad19ca34f8ff4c3a", "Simple Way of Piece life", listOf(), ""),
            Books("67ab122eacd3cb34a8ddc8d4", "Simple Way of Piece life", listOf(), ""),
            Books("67ab125ce41b4d34e48991c7", "Simple Way of Piece life", listOf(), ""),
            Books("67ab1285ad19ca34f8ff4cd2", "Simple Way of Piece life", listOf(), ""),
            Books("67ab12b6e41b4d34e4899211", "Simple Way of Piece life", listOf(), "")
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