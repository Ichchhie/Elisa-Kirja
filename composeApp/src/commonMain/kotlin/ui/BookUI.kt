package ui

import API.GetBooks
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
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
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.ColorFilter

class BookUI {
    // UI for a single book item
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun bookItem(product: Books, onItemClick: (Books) -> Unit) {
        val imageUrl = "https://api.codetabs.com/v1/proxy/?quest=" + product.coverThumbnailImage
        val customColors = LocalCustomColors.current

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                Modifier.fillMaxWidth().background(MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .size(height = 250.dp, width = 200.dp).background(MaterialTheme.colors.background),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalPlatformContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .crossfade(800)
                            .build(),
                        placeholder = painterResource(Res.drawable.book),
                        error = painterResource(Res.drawable.book),
                        fallback = painterResource(Res.drawable.book),
                        contentDescription = "${product.title} available",
                        modifier = Modifier
                            .fillMaxSize().background(MaterialTheme.colors.background),
                        contentScale = ContentScale.Crop
                    )
                }
                product.title?.let {
                    Text(
                        modifier = Modifier.padding(top = 12.dp).background(MaterialTheme.colors.background),
                        text = it,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h5
                    )
                }
                Text(text = "hello", style = MaterialTheme.typography.body1)
                Row {
                    repeat(4) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Rating",
                        tint = MaterialTheme.colors.secondary
                    )
                }
                Row(
                    modifier = Modifier.padding(2.dp).background(MaterialTheme.colors.background)
                ) {
                    Image(
                        modifier = Modifier.size(20.dp).background(MaterialTheme.colors.background),
                        painter = painterResource(Res.drawable.headphones),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                    )
                    Image(
                        modifier = Modifier.size(20.dp).background(MaterialTheme.colors.background),
                        painter = painterResource(Res.drawable.menu_book),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                    )
                }
            }
        }
    }

    // UI for a single book category item
    @Composable
    fun categoryItem(category: BookCategory) {
        val navigator = LocalNavigator.currentOrThrow
        val bookContainers = rememberSaveable() { mutableStateOf<List<BookContainer?>>(emptyList()) }
        var isLoading by rememberSaveable() { mutableStateOf(true) }

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
            if (bookContainers.value.isNullOrEmpty()) {
                delay(1000)
            }
            isLoading = false
        }

        Column(Modifier.fillMaxWidth().background(MaterialTheme.colors.background), horizontalAlignment = Alignment.CenterHorizontally) {
            if (isLoading) {
                repeat(1) {
//                    todo
//                    Shimmer().ShimmerPlaceholder()
                    LoadingEffect().LoadingAnimation()
                }
            } else {
                Spacer(Modifier.height(24.dp).background(MaterialTheme.colors.background))
                Text(category.categoryDesc)
                Spacer(Modifier.height(12.dp).background(MaterialTheme.colors.background))
                Text(category.categoryName)
                Spacer(Modifier.height(12.dp).background(MaterialTheme.colors.background))

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
        }

        Column(
            Modifier.fillMaxWidth().padding(top = 4.dp, end = 16.dp).background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.End
        ) {
            ClickableText(
                text = AnnotatedString(category.viewAllText),
                onClick = {
                    navigator.push(AllBooksScreen())
                },
                style = TextStyle(
                    color = MaterialTheme.colors.onBackground,
                )
            )
        }
    }

    @Composable
    fun allBooksItem() {
        val bookContainers = remember { mutableStateOf<AllBooksContainer?>(null) }
        var isLoading by remember { mutableStateOf(true) }
        // Fetch book data with LaunchedEffect
        LaunchedEffect("all_books") {
            val books = coroutineScope {
                async {
                    GetBooks().retrieveAllBooksOfCategory("483")
                }
            }
            bookContainers.value = books.await()
            isLoading = false
        }
        if (isLoading) {
            repeat(1) {
//                Shimmer().ShimmerPlaceholder()
                 Column(Modifier.fillMaxWidth().fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    LoadingEffect().LoadingAnimation()
                }
            }
        } else {
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
                Text("data fetching")
                // Show a loading indicator or an error message
            }
        }
    }
}

