package ui

import BookCategory
import DummyBook
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BookUI {

    //ui of single book item
    @Composable
    fun bookItem(product: DummyBook, onItemClick: (DummyBook) -> Unit) {
        // Your item UI code here
        // Detect click and invoke the onItemClick lambda
        Column(
            modifier = Modifier
                .clickable { onItemClick(product) }
                .padding(16.dp)
        ) {
            Text(text = product.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "$${product.price}", fontSize = 14.sp, color = Color.Gray)
        }
    }

    //ui of single book category item
    @Composable
    fun categoryItem(category: BookCategory) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(24.dp))
            Text(category.categoryDesc)
            Spacer(Modifier.height(16.dp))
            Text(category.categoryName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            LazyRow() {
                items(category.books) { product ->
                   bookItem(product = product, onItemClick = { selectedProduct ->
                        // Handle item click
                        // You can navigate to a detail screen, show a dialog, etc.
                    })
                }
            }
        }
        Column(Modifier.fillMaxWidth().padding(top = 8.dp, end = 16.dp), horizontalAlignment = Alignment.End) {
            Text(category.viewAllText, color = Color.Magenta, textAlign = TextAlign.End)
        }
    }
}