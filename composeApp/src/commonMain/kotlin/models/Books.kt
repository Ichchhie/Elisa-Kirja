package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookContainer(
    val book: Books
)
@Serializable
data class AllBooksContainer(
    val record: AllBooksData
)
@Serializable
data class AllBooksData(
    val books: List<Books>,
    val total: Int,
    val totalEbooks: Int,
    val totalAudioBooks: Int
)

@Serializable
data class Books(
    @SerialName("id")
    var id: String? = null,

    //val path: String,
    //val name: String,
    @SerialName("title")
    var title: String? = null,
    //val author: String

    // val price: Double,
    // val publishedTimestamp: String,
    // val description: String,
    //val authors: List<Author>
//    val authors: String,
//    val coverImage: String,
//    val coverImageFull: String,
//    val coverImageAttr: String,
    @SerialName("coverImage")
    var coverImage: String? = null,

    @SerialName("coverThumbnailImage")
    var coverThumbnailImage: String? = null,
//    val coverMiniThumbnailImage: String,
//    val coverImage800: String,
)






