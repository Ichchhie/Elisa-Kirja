
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import wasmdemo.composeapp.generated.resources.Res
import wasmdemo.composeapp.generated.resources.book
import wasmdemo.composeapp.generated.resources.headphones
import wasmdemo.composeapp.generated.resources.menu_book


/* Static data */

@Immutable
data class Books (
    val name: String,
    val description: String,
    val author: String
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ElisaBooks(books: Books) {
    Row (
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // The book
            Card (
                modifier = Modifier
                    .size(height = 300.dp, width = 250.dp)
                    .fillMaxSize(),
            ){
                Image(
                    painter = painterResource(Res.drawable.book),
                    contentDescription = "${books.name} available",
                    modifier = Modifier
                        .size(20.dp) // size of the image
                        .padding(30.dp),
                    contentScale = ContentScale.Crop // Crop the image if necessary to fit
                )
            }

            // The text
            Text( modifier = Modifier
                .padding(top = 12.dp),
                text = books.name,
                fontWeight = FontWeight.Bold
            )
            Text(text = books.author, fontSize = 15.sp)
            Row {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Rating", tint = Color.Blue)
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Rating", tint = Color.Blue)
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Rating", tint = Color.Blue)
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Rating", tint = Color.Blue)
                Icon(imageVector = Icons.Outlined.Star, contentDescription = "Rating", tint = Color.Blue)
            }
            Image(modifier = Modifier
                .size(10.dp),
                painter = painterResource(Res.drawable.headphones),
                contentDescription = null
            )
            Image(modifier = Modifier
                .size(10.dp),
                painter = painterResource(Res.drawable.menu_book),
                contentDescription = null
            )
        }
    }
}

@Composable
fun bookList(bookList: List<Books>) {
    LazyRow {
        items(bookList) { bookDetails ->
            ElisaBooks(books = bookDetails)
        }
    }
}