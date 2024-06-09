package ui.screens

import Strings
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
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
import ui.Animations
import ui.BookUI
import ui.LocalCustomColors
import wasmdemo.composeapp.generated.resources.Res
import wasmdemo.composeapp.generated.resources.arrow_new
import wasmdemo.composeapp.generated.resources.audiobook

@OptIn(ExperimentalResourceApi::class)
class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var showContent by rememberSaveable() { mutableStateOf(false) }
        var firstLoad by remember { mutableStateOf(true) }
        val allBooks = rememberSaveable() { mutableStateOf<List<BookContainer?>>(emptyList()) }
        val customColors = LocalCustomColors.current
        val screenModel = rememberScreenModel() { BooksViewModel() }
        val listState = rememberLazyListState()
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        val density = LocalDensity.current
        //animation variables
        val infiniteTransition = rememberInfiniteTransition()
        val dy by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        val travelDistance = with(LocalDensity.current) { 40.dp.toPx() }

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
        LazyColumn(
            state = listState
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(customColors.secondaryBackground),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val greeting = remember { Strings().greet() }
                        Row(
                            Modifier
                                .fillMaxHeight()
                                .padding(horizontal = 42.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                Modifier
                                    .weight(0.6f, fill = true)
                                    .padding(42.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Animations().TypewriteText(
                                    greeting,
                                    style = TextStyle(
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 54.sp,
                                        color = MaterialTheme.colors.primary,
                                        lineHeight = 40.sp,
                                    )
                                )
                                Spacer(Modifier.height(24.dp))
                                Text(
                                    Strings().elisaDescription(),
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colors.secondary,
                                    fontWeight = FontWeight.W500,
                                    lineHeight = 30.sp,
                                )
                                Spacer(Modifier.height(36.dp))
                                BookUI().returnButton(Strings().browseBooks(), 45.dp, navigator)
                            }
                            Column(
                                Modifier.weight(0.4f, fill = true),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(painterResource(Res.drawable.audiobook), null)
                                Spacer(Modifier.height(8.dp))
                                if (showContent) {
                                    Box(modifier = Modifier.align(Alignment.End)) {
                                        FloatingActionButton(
                                            modifier = Modifier
                                                .graphicsLayer {
                                                    translationY = dy * travelDistance
                                                },
                                            onClick = {
                                                coroutineScope.launch {
                                                    // Animate scroll to the 10th item
                                                    listState.animateScrollToItem(index = 1)
                                                }
                                            },
                                        ) {
                                            Icon(
                                                painterResource(Res.drawable.arrow_new),
                                                "Floating action button."
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (showContent) {
                items(Strings().getBookCategories()) { category ->
                    BookUI().categoryItem(category, allBooks.value)
                }
            }
        }
    }
}

