import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Greeting {
//    private val platform = getPlatform()

    fun greet(): String {
        return "WELCOME TO THE ELISA BOOK\n" +
                "The most popular Finnish audio and e-book service!"
    }
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
}