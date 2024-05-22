// Shimmer.kt
package ui

import androidx.compose.animation.core.*
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberInfiniteTransition
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class Shimmer {

    @Composable
    fun ShimmerPlaceholder() {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition()
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(800), repeatMode = RepeatMode.Reverse
            )
        )

        val brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(brush, RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier
                .height(24.dp)
                .fillMaxWidth(0.5f)
                .background(brush, RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier
                .height(24.dp)
                .fillMaxWidth(0.7f)
                .background(brush, RoundedCornerShape(16.dp))
            )
        }
    }
}
