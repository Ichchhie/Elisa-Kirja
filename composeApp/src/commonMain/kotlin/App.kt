
//import io.ktor.client.engine.cio.*
import API.GetBooks
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
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

    //BookUI().RetrieveBooksFromAPI()
        MaterialTheme {
            var showContent by remember { mutableStateOf(false) }

            // changed Column to LazyColumn for vertical scrolling of the page
            LazyColumn(Modifier.fillMaxHeight()) {
                item {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { showContent = !showContent }) {
                            Text("What's Elisa? ")
                        }
                        AnimatedVisibility(showContent) {
                            val scope = rememberCoroutineScope()
                            var greeting by remember { mutableStateOf("...") }
                            //val greeting = remember { Greeting().greet() }
                           scope.launch{
                               //greeting = Greeting().greet()
                               greeting = GetBooks().RetrieveBooksFromAPI()
                            }
                            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(painterResource(Res.drawable.elisa), null)

                                Text("$greeting")
                            }
                        }
                    }
                }
                //val scope = rememberCoroutineScope()
                //var  greeting by remember { mutableStateOf("...") }
                //scope.launch { greeting = BookUI().RetrieveBooksFromAPI() }
                items(Greeting().getBookCategories()) { product ->
                    BookUI().categoryItem(product)
                }
            }
        }

}





