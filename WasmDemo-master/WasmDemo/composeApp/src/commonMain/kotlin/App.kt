import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.BookUI
import wasmdemo.composeapp.generated.resources.Res
import wasmdemo.composeapp.generated.resources.elisa

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val bookList = listOf(
            DummyBook(1, "Harry Potter", 599.99),
            DummyBook(2, "Harry Potter", 1299.99),
            DummyBook(3, "Harry Potter", 99.99),
            DummyBook(1, "Harry Potter", 599.99),
            DummyBook(2, "Harry Potter", 1299.99),
            DummyBook(3, "Harry Potter", 99.99),
            DummyBook(1, "Harry Potter", 599.99),
            DummyBook(2, "Harry Potter", 1299.99),
            DummyBook(3, "Harry Potter", 99.99),
            // Add more items as needed
        )
        val categoryList = listOf(
            BookCategory(1, "OUR TOP-SELLING COLLECTION", "Detective Books", bookList, "View all Detective books ->"),
            BookCategory(2, "OUR MOST-LOVED COLLECTION", "Educational Books", bookList, "View all Educational Books ->"),)

        // changed Column to LazyColumn for vertical scrolling of the page
        LazyColumn(Modifier.fillMaxHeight()) {
            item {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = { showContent = !showContent }) {
                        Text("What's Elisa? ")
                    }
                    AnimatedVisibility(showContent) {
                        val greeting = remember { Greeting().greet() }
                        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(painterResource(Res.drawable.elisa), null)
                            Text("$greeting")
                        }
                    }
                }
            }
            items(categoryList) { product ->
                BookUI().categoryItem(product)
            }
        }

    }
}





