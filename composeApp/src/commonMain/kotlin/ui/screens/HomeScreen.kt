package ui.screens

import Strings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import models.BookContainer
import models.BooksViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.BookUI
import ui.LocalCustomColors
import wasmdemo.composeapp.generated.resources.Res
import wasmdemo.composeapp.generated.resources.arrow_new
import wasmdemo.composeapp.generated.resources.arrow_right
import wasmdemo.composeapp.generated.resources.audiobook

@OptIn(ExperimentalResourceApi::class)
class HomeScreen : Screen {
    @Composable
    override fun Content() {
        var showContent by rememberSaveable() { mutableStateOf(false) }
        val allBooks = rememberSaveable() { mutableStateOf<List<BookContainer?>>(emptyList()) }
        val customColors = LocalCustomColors.current
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel() { BooksViewModel() }
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        val density = LocalDensity.current

        // Observe the allBooks state flow and update the UI when data is loaded
        LaunchedEffect(screenModel.allBooks) {
            screenModel.allBooks.collect { books ->
                if (screenModel.isDataLoaded()) {
                    // Update the UI here
                    showContent = true
                    allBooks.value = books
                }

            }

        }

        // Main UI of the page
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colors.background)
                .then(if (showContent) Modifier.verticalScroll(scrollState) else Modifier)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(customColors.secondaryBackground)
                    .padding(horizontal = 42.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val greeting = remember { Strings().greet() }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 42.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier
                                .weight(0.6f, fill = true)
                                .padding(42.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                greeting,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 54.sp,
                                color = MaterialTheme.colors.primary,
                                lineHeight = 40.sp,
                            )
                            Spacer(Modifier.height(24.dp))
                            Text(
                                Strings().elisaDescription(),
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.secondary,
                                fontWeight = FontWeight.W500,
                                lineHeight = 30.sp,
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
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                            ) {
                                Text(
                                    Strings().browseBooks(),
                                    Modifier.padding(end = 10.dp),
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                                )
                                Image(
                                    painterResource(Res.drawable.arrow_right),
                                    contentDescription = "browse button icon",
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                                )
                            }
                        }
                        Column(
                            Modifier.weight(0.4f, fill = true),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(painterResource(Res.drawable.audiobook), null)
                            if (showContent) {
                                Spacer(Modifier.height(16.dp))
                                Card(
                                    shape = CircleShape,
                                    backgroundColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clickable {
                                            coroutineScope.launch {
                                                val scrollOffset =
                                                    with(density) { 500.dp.toPx().toInt() }
                                                scrollState.animateScrollTo(scrollOffset)
                                            }
                                        }
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Image(
                                            painterResource(Res.drawable.arrow_new),
                                            contentDescription = "down arrow",
                                            modifier = Modifier.size(24.dp),
                                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (showContent) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Strings().getBookCategories().forEach { category ->
                        BookUI().categoryItem(category, allBooks.value)
                    }
                }
            }
        }
    }
}
