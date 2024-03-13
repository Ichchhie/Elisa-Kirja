

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable

fun BookCard() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            MaterialTheme {
                val bookProfile = listOf(
                    Books("Simple Way of Piece life", "Simple Way of Piece life","Armor Ramsey"),
                    Books("Simple Way of Piece life", "Simple Way of Piece life","Armor Ramsey"),
                    Books("Simple Way of Piece life", "Simple Way of Piece life","Armor Ramsey"),
                    )
                MaterialTheme {
                    bookList(bookProfile)
                }
            }
        }
    }
}


