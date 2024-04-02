package models

import androidx.compose.runtime.Immutable

/* Static data */

data class BookContainer(
    val book: Books
)

@Immutable
data class Books(
    val id: Int,
    //val path: String,
    //val name: String,
    val title: String,
    //val author: String

   // val price: Double,
   // val publishedTimestamp: String,
   // val description: String,
   //val authors: List<Author>
    val authors: String
)






