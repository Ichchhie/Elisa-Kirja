package ui

import Greeting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import wasmdemo.composeapp.generated.resources.Res
import wasmdemo.composeapp.generated.resources.arrow_right
import wasmdemo.composeapp.generated.resources.audiobook
import wasmdemo.composeapp.generated.resources.elisa
import wasmdemo.composeapp.generated.resources.menu_book
@OptIn(ExperimentalResourceApi::class)
class HomeScreen: Screen {
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
//        val booksViewModel = remember { BooksViewModel() }
        var showContent by remember { mutableStateOf(false) }
        var showBooks by remember { mutableStateOf(false) }
        val navigator = LocalNavigator.currentOrThrow
        var isLoading by remember { mutableStateOf(true) }

        // Simulate loading delay
        LaunchedEffect(Unit) {
//            delay(2000) // Simulate initial network delay
            showContent = true

            //delay(1000)
            isLoading = false
        }
        LazyColumn(Modifier.fillMaxHeight()) {
            item {
                NavBar().DisplayNavBar()
            }
            item {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    AnimatedVisibility(visible = showContent) {
                        val greeting = remember { Greeting().greet() }
                        Row(Modifier.fillMaxWidth().background(color = Color.Blue).padding(horizontal = 42.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(
                                Modifier.weight(0.6F, fill = true).padding(42.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    "$greeting",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 64.sp,
                                    color = Color.White,
                                    lineHeight = 42.sp,
                                )
                                Spacer(Modifier.height(24.dp))
                                Text(
                                    Greeting().elisaDescription(),
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.W500,
                                    lineHeight = 32.sp,
                                )
                                Spacer(Modifier.height(24.dp))
                                Button(
                                    onClick = { navigator.push(AllBooksScreen()) },
                                    elevation = ButtonDefaults.elevation(
                                        defaultElevation = 10.dp,
                                        pressedElevation = 15.dp,
                                        disabledElevation = 0.dp
                                    ),
                                    shape = RoundedCornerShape(20.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                                ) {
                                    Text(
                                        Greeting().browseBooks(),
                                        Modifier.padding(end = 10.dp),
                                        color = Color.Blue,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Image(
                                        painterResource(Res.drawable.arrow_right),
                                        contentDescription = "browse button icon",
                                        modifier = Modifier.size(24.dp)
                                    )

                                }
                            }
                            Column(
                                Modifier.weight(0.4F, fill = true),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(painterResource(Res.drawable.audiobook), null)
                            }
                        }
                    }
                }
            }
            if (showContent) {
                if (isLoading) {
                    items(5) { //  5 shimmer placeholders
                        Shimmer().ShimmerPlaceholder()
                    }
                } else {
                    items(Greeting().getBookCategories()) { category ->
                        BookUI().categoryItem(category)
                    }
                }
            }
        }
    }
}
