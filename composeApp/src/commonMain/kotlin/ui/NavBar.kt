package ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class NavBar {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
    fun DisplayNavBar() {
        var text by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .background(color = Color.Blue)
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(8.dp)) // Margin between items
                /*IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description"
                    )
                }
                 */
                ClickableText(
                    onClick = {/*Do nothing for now */},
                    style = TextStyle(
                        fontSize = 20.sp, // Setting the font size to 20sp
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    ),
                    text = AnnotatedString("Elisa Kirja"),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


            SearchBar(
                query = text,
                onQueryChange = {text = it},
                onSearch = {
                    active = false
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = { Text("Search...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    if (active) {
                        IconButton(onClick = { text = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Close Icon"
                            )
                        }
                    }
                },
            ){

            }


            Spacer(modifier = Modifier.width(22.dp)) // Horizontal space between SearchBar and Texts
            //Spacer(modifier = Modifier.height(22.dp)) // Margin between items



            ClickableText(
                onClick = { },
                text = AnnotatedString("E-books"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 15.sp, // Setting the font size to 20sp
                    color = Color.White
                )
            )
            ClickableText(
                onClick = { },
                text = AnnotatedString("Audio Books"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 15.sp, // Setting the font size to 20sp
                    color = Color.White
                )
            )
            ClickableText(
                onClick = { },
                text = AnnotatedString("Buy Books"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 15.sp, // Setting the font size to 20sp
                    color = Color.White
                )
            )
            ClickableText(
                onClick = { },
                text = AnnotatedString("Contact Us"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 15.sp, // Setting the font size to 20sp
                    color = Color.White
                )
            )
            Button(
                onClick = { /* doSomething() */ },
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Blue,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray
                ),

            )
            {
                Text(
                    "Login",
                    color = Color.Blue
                )
            }
            Spacer(modifier = Modifier.height(8.dp)) // Margin between items
        }
    }

}