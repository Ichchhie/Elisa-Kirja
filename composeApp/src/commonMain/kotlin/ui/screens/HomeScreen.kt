package ui.screens

import Greeting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.BookUI
import ui.LocalCustomColors
import wasmdemo.composeapp.generated.resources.Res
import wasmdemo.composeapp.generated.resources.arrow_right
import wasmdemo.composeapp.generated.resources.arrow_new
import wasmdemo.composeapp.generated.resources.audiobook

@OptIn(ExperimentalResourceApi::class)
class HomeScreen : Screen {
    @Composable
    override fun Content() {
        var showContent by remember { mutableStateOf(false) }
        val customColors = LocalCustomColors.current
        val navigator = LocalNavigator.currentOrThrow
        var isLoading by remember { mutableStateOf(true) }
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        val density = LocalDensity.current
        var canScroll by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            // Simulating loading time
            kotlinx.coroutines.delay(1000)
            showContent = true
            isLoading = false
        }

        // Main UI of the page
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colors.background)
                .then(if (canScroll) Modifier.verticalScroll(scrollState) else Modifier)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(customColors.secondaryBackground)
                    .padding(horizontal = 42.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showContent) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val greeting = remember { Greeting().greet() }
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
                                    Greeting().elisaDescription(),
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
                                        Greeting().browseBooks(),
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
                            }
                        }
                        Spacer(Modifier.height(24.dp))
                        // Down arrow image inside the blue section
                        if (!isLoading) {
                            Spacer(Modifier.height(16.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Card(
                                    shape = CircleShape,
                                    backgroundColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clickable {
                                            coroutineScope.launch {
                                                canScroll = true
                                                val scrollOffset = with(density) { 500.dp.toPx().toInt() }
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
            if (showContent && !isLoading) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Greeting().getBookCategories().forEach { category ->
                        BookUI().categoryItem(category)
                    }
                }
            }
        }
    }
}
