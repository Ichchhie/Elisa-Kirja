package ui

import API.GetBooks
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import models.AllBooksContainer
import models.BookCategory
import models.BookContainer
import models.Books
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import wasmdemo.composeapp.generated.resources.Res
import wasmdemo.composeapp.generated.resources.book
import wasmdemo.composeapp.generated.resources.headphones
import wasmdemo.composeapp.generated.resources.menu_book

class BookUI {
    // UI for a single book item
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun bookItem(product: Books, onItemClick: (Books) -> Unit) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .size(height = 250.dp, width = 200.dp),
                ) {
                    AsyncImage(
                        model = "https://api.codetabs.com/v1/proxy/?quest=" + product.coverThumbnailImage,
                        placeholder = painterResource(Res.drawable.book),
                        error = painterResource(Res.drawable.book),
                        fallback = painterResource(Res.drawable.book),
                        contentDescription = "${product.title} available",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                product.title?.let {
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = it,
                        style = AppStyles.h5Style
                    )
                }
                Text(text = "hello", style = AppStyles.bodyStyle)
                Row {
                    repeat(4) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = AppStyles.primaryButtonColor
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Rating",
                        tint = AppStyles.primaryButtonColor
                    )
                }
                Row(
                    modifier = Modifier.padding(2.dp)
                ) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(Res.drawable.headphones),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(Res.drawable.menu_book),
                        contentDescription = null
                    )
                }
            }
        }
    }

    // UI for a single book category item
    @Composable
    fun categoryItem(category: BookCategory) {
        val navigator = LocalNavigator.currentOrThrow
        val bookContainers = remember { mutableStateOf<List<BookContainer?>>(emptyList()) }

        // Fetch book data with LaunchedEffect
        LaunchedEffect(category) {
            val books = category.books.map { book ->
                coroutineScope {
                    async {
                        GetBooks().retrieveBooksFromAPI(book.id)
                    }
                }
            }
            bookContainers.value = books.awaitAll()
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(24.dp))
            Text(category.categoryDesc)
            Spacer(Modifier.height(12.dp))
            Text(category.categoryName, style = AppStyles.headingStyle)
            Spacer(Modifier.height(12.dp))

            LazyRow {
                itemsIndexed(bookContainers.value) { index, bookContainer ->
                    val product = category.books[index]
                    product.coverThumbnailImage = bookContainer?.book?.coverThumbnailImage
                    bookItem(
                        product = product,
                        onItemClick = {
                            // Handle item click
                        }
                    )
                }
            }
        }

        Column(
            Modifier.fillMaxWidth().padding(top = 4.dp, end = 16.dp),
            horizontalAlignment = Alignment.End
        ) {
            ClickableText(
                text = AnnotatedString(category.viewAllText),
                onClick = {
                    navigator.push(AllBooksScreen())
                },
                style = TextStyle(
                    color = AppStyles.primaryButtonColor,
                )
            )
        }
    }

    @Composable
    fun allBooksItem() {
        val bookContainers = remember { mutableStateOf<AllBooksContainer?>(null) }
        // Fetch book data with LaunchedEffect
        LaunchedEffect("all_books") {
            val books = coroutineScope {
                async {
                    GetBooks().retrieveAllBooksOfCategory("483")
                }
            }
            bookContainers.value = books.await()
        }
        if (bookContainers != null) {
            val list = bookContainers.value?.record?.books
            list?.let { books ->
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 250.dp),
                    content = {
                        items(books.size) { item ->
                            bookItem(books[item], onItemClick = {
                                // handle item click
                            })
                        }
                    }
                )
            }
        } else {
            Text("data fetching")
            // Show a loading indicator or an error message
        }
    }
}

