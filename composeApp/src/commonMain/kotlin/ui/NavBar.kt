package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NavBar {

    @Composable
    fun DisplayNavBar(isDarkTheme: Boolean, toggleTheme: () -> Unit) {
        var text by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }
        val customColors = LocalCustomColors.current

        Row(
            modifier = Modifier
                .background(color = customColors.secondaryBackground)
                .fillMaxWidth()
                .padding(10.dp)
                .pointerInput(Unit) {
                    coroutineScope {
                        launch {
                            detectTapGestures(onTap = {
                                text = ""
                                active = false
                            })
                        }
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(8.dp)) // Margin between items
            ClickableText(
                onClick = {/* Do nothing for now */ },
                style = TextStyle(
                    fontSize = 20.sp, // Setting the font size to 20sp
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                ),
                text = AnnotatedString("Elisa Kirja"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Search...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        IconButton(onClick = {
                            text = ""
                            active = false
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Icon"
                            )
                        }
                    }
                },
                modifier = Modifier
                    .width(300.dp)
                    .background(customColors.secondaryBackground, shape = RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(24.dp), // Set the rounded corners
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        active = false
                    }
                )
            )

            Spacer(modifier = Modifier.width(22.dp)) // Horizontal space between SearchBar and Texts

            ClickableText(
                onClick = { },
                text = AnnotatedString("E-books"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.White
                )
            )
            ClickableText(
                onClick = { },
                text = AnnotatedString("Audio Books"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.White
                )
            )
            ClickableText(
                onClick = { },
                text = AnnotatedString("Buy Books"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.White
                )
            )
            ClickableText(
                onClick = { },
                text = AnnotatedString("Contact Us"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.White
                )
            )
            Button(
                onClick = { /* doSomething() */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Blue,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray
                ),
            ) {
                Text(
                    "Login",
                    style = MaterialTheme.typography.button
                )
            }
            // Add the theme toggle button to the NavBar
            Button(
                onClick = toggleTheme,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isDarkTheme) Color.Gray else Color.White,
                    contentColor = if (isDarkTheme) Color.White else Color.Black
                )
            ) {
                Text(if (isDarkTheme) "Light Mode" else "Dark Mode")
            }
            Spacer(modifier = Modifier.height(8.dp)) // Margin between items
        }
    }
}
