package ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
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
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import wasmdemo.composeapp.generated.resources.Res
import wasmdemo.composeapp.generated.resources.arrow_right

class Animations{
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun HorizontalBouncingIcon(infiniteTransition: InfiniteTransition) {
        val startColor = Color.Green
        val endColor = Color.Black
        val animatedColor by infiniteTransition.animateColor(
            initialValue = startColor,
            targetValue = endColor,
            animationSpec = infiniteRepeatable(
                tween(800, easing = FastOutLinearInEasing),
                RepeatMode.Reverse,
            )
        )
        val position by infiniteTransition.animateFloat(
            initialValue = -10f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    800,
                    easing = FastOutLinearInEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Hear icon",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .size(24.dp)
                .offset(x = position.dp)
        )
    }
    @Composable
    fun TypewriteText(
        text: String,
        modifier: Modifier = Modifier,
        isVisible: Boolean = true,
        spec: AnimationSpec<Int> = tween(durationMillis = text.length * 100, easing = LinearEasing),
        style: TextStyle = LocalTextStyle.current,
        preoccupySpace: Boolean = true
    )  {
        // State that keeps the text that is currently animated
        var textToAnimate by remember { mutableStateOf("") }

        // Animatable index to control the progress of the animation
        val index = remember {
            Animatable(initialValue = 0, typeConverter = Int.VectorConverter)
        }

        // Effect to handle animation when visibility changes
        LaunchedEffect(isVisible) {
            if (isVisible) {
                // Start animation if visible
                textToAnimate = text
                index.animateTo(text.length, spec)
            } else {
                // Snap to the beginning if not visible
                index.snapTo(0)
            }
        }

        // Effect to handle animation when text content changes
        LaunchedEffect(text) {
            if (isVisible) {
                // Reset animation and update text if visible
                index.snapTo(0)
                textToAnimate = text
                index.animateTo(text.length, spec)
            }
        }

        // Box composable to contain the animated and static text
        Box(modifier = modifier) {
            if (preoccupySpace && index.isRunning) {
                // Display invisible text when preoccupation is turned on
                // and the animation is in progress.
                // Plays the role of a placeholder to occupy the space
                // that will be filled with text.
                Text(
                    text = text,
                    style = style,
                    modifier = Modifier.alpha(0f),
                )
            }

            // Display animated text based on the current index value
            Text(
                text = textToAnimate.substring(0, index.value),
                style = style
            )
        }
    }
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
            transitionSpec = { tween(durationMillis = 500, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 1"
        ) { state -> if (state) 0f else 600f }

        val offsetY2 by transition2.animateFloat(
            transitionSpec = { tween(durationMillis = 500, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 2"
        ) { state -> if (state) 0f else 600f }

        val offsetY3 by transition3.animateFloat(
            transitionSpec = { tween(durationMillis = 450, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 3"
        ) { state -> if (state) 0f else 600f }

        val offsetY4 by transition4.animateFloat(
            transitionSpec = { tween(durationMillis = 500, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 4"
        ) { state -> if (state) 0f else 600f }

        val offsetY5 by transition5.animateFloat(
            transitionSpec = { tween(durationMillis = 500, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 5"
        ) { state -> if (state) 0f else 600f }

        val offsetY6 by transition6.animateFloat(
            transitionSpec = { tween(durationMillis = 500, easing = LinearOutSlowInEasing) },
            label = "OffsetY Animation 6"
        ) { state -> if (state) 0f else 600f }

        val backgroundOffsetY by backgroundTransition.animateFloat(
            transitionSpec = { tween(durationMillis = 500, easing = LinearOutSlowInEasing) },
            label = "Background OffsetY Animation"
        ) { state -> if (state) -600f else 0f }

        LaunchedEffect(true) {
            delay(100)
            transitionState1.targetState = true
            delay(300)
            transitionState2.targetState = true
            delay(300)
            transitionState3.targetState = true
            delay(300)
            transitionState4.targetState = true
            delay(200)
            transitionState5.targetState = true
            delay(200)
            transitionState6.targetState = true
            delay(1000)
            backgroundTransitionState.targetState = true
            delay(100) // Duration of background animation
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
