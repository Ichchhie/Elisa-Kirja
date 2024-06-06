package ui

import API.GetBooks
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import models.AllBooksContainer
import models.BookCategory
import models.BookContainer
import models.Books
class TextAnimation {

    @Composable
    fun SplashScreen(onAnimationEnd: () -> Unit) {
        val transitionState1 = remember { MutableTransitionState(false) }
        val transitionState2 = remember { MutableTransitionState(false) }
        val transitionState3 = remember { MutableTransitionState(false) }
        val transitionState4 = remember { MutableTransitionState(false) }
        val transitionState5 = remember { MutableTransitionState(false) }
        val transitionState6 = remember { MutableTransitionState(false) }
        val backgroundTransitionState = remember { MutableTransitionState(false) }

        val transition1 = updateTransition(transitionState1, label = "Text Transition 1")
        val transition2 = updateTransition(transitionState2, label = "Text Transition 2")
        val transition3 = updateTransition(transitionState3, label = "Text Transition 3")
        val transition4 = updateTransition(transitionState4, label = "Text Transition 4")
        val transition5 = updateTransition(transitionState5, label = "Text Transition 5")
        val transition6 = updateTransition(transitionState6, label = "Text Transition 6")
        val backgroundTransition = updateTransition(backgroundTransitionState, label = "Background Transition")

        val offsetY1 by transition1.animateFloat(
            transitionSpec = { tween(durationMillis = 1000, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 1"
        ) { state -> if (state) 0f else 600f }

        val offsetY2 by transition2.animateFloat(
            transitionSpec = { tween(durationMillis = 1000, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 2"
        ) { state -> if (state) 0f else 600f }

        val offsetY3 by transition3.animateFloat(
            transitionSpec = { tween(durationMillis = 850, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 3"
        ) { state -> if (state) 0f else 600f }

        val offsetY4 by transition4.animateFloat(
            transitionSpec = { tween(durationMillis = 1000, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 4"
        ) { state -> if (state) 0f else 600f }

        val offsetY5 by transition5.animateFloat(
            transitionSpec = { tween(durationMillis = 1000, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 5"
        ) { state -> if (state) 0f else 600f }

        val offsetY6 by transition6.animateFloat(
            transitionSpec = { tween(durationMillis = 1000, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 6"
        ) { state -> if (state) 0f else 600f }

        val backgroundOffsetY by backgroundTransition.animateFloat(
            transitionSpec = { tween(durationMillis = 1000, easing = LinearOutSlowInEasing) },
            label = "Background OffsetY Animation"
        ) { state -> if (state) -600f else 0f }

        LaunchedEffect(true) {
            delay(500)
            transitionState1.targetState = true
            delay(700)
            transitionState2.targetState = true
            delay(700)
            transitionState3.targetState = true
            delay(700)
            transitionState4.targetState = true
            delay(600)
            transitionState5.targetState = true
            delay(600)
            transitionState6.targetState = true
            delay(3000)
            backgroundTransitionState.targetState = true
            delay(500) // Duration of background animation
            onAnimationEnd()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF6E85B7))
                .offset(y = backgroundOffsetY.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier
                ) {
                    Text(
                        text = "We are",
                        color = Color.White,
                        fontSize = 60.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.offset(y = offsetY1.dp)
                    )
                    Text(
                        text = "Elisa",
                        color = Color.White,
                        fontSize = 60.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.offset(y = offsetY3.dp)
                    )
                    Text(
                        text = "Kirja",
                        color = Color.White,
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.offset(y = offsetY2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier
                ) {
                    Text(
                        text = "Finland's",
                        color = Color.White,
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.offset(y = offsetY4.dp)
                    )
                    Text(
                        text = "Largest Online",
                        color = Color.White,
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.offset(y = offsetY5.dp)
                    )
                    Text(
                        text = "Bookstore",
                        color = Color.White,
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.offset(y = offsetY6.dp)
                    )
                }
            }
        }


    }
}
