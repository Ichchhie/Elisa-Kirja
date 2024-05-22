package ui

import androidx.compose.material.Colors
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class AppStyles {
    companion object {

        val fontFamily = FontFamily.Default

        data class CustomColors(
            val primaryBackground: Color,
            val secondaryBackground: Color,
            val materialColors: Colors
        )

        fun getLightColors() = CustomColors(
            primaryBackground = Color(0xFFB2C8DF), //lightBlue
            secondaryBackground = Color(0xFF6E85B7), //darkerdarkerBlue
            materialColors = lightColors(
                primary = Color(0xFFF8F9D7), //white cream
                primaryVariant = Color(0xFF001F91),
                secondary = Color(0xFFC4D7E0), //light light blue
                background = Color.White,//darkerBlue
                surface = Color.White,
                onPrimary = Color.White,
                onSecondary = Color.Black,
                onBackground = Color(0xFF1F2634),
                onSurface = Color.Black
            )
        )

        fun getDarkColors() = CustomColors(
            primaryBackground = Color(0xFF37474F), //darkish grey blue
            secondaryBackground = Color(0xFF263238), //dark dark grey blue
            materialColors = darkColors(
                primary = Color(0xFF76ABAE), //turquoise
                primaryVariant = Color(0xFF3700B3),
                secondary = Color(0xFFEEEEEE), //gray
                background = Color(0xFF37474F), //darkish gray blue
                surface = Color.Black,
                onPrimary = Color.Black,
                onSecondary = Color.Black,
                onBackground = Color(0xFFEEEEEE),
                onSurface = Color.White
            )
        )

        fun getLightTypography() = Typography(
            body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Black
            ),
            h1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.Black
            ),
            h2 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            ),
            h3 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            ),
            h4 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            ),
            h5 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF1F2634)
            ),
            h6 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            ),
            subtitle1 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = Color.Black
            )
            // Add other text styles as needed
        )

        fun getDarkTypography() = Typography(
            body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White
            ),
            h1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.White
            ),
            h2 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            ),
            h3 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            ),
            h4 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            ),
            h5 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            ),
            h6 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            ),
            subtitle1 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = Color.Black
            )
            // Add other text styles as needed
        )

        val primaryButtonColor = Color(0xFF0025AE) // Set primary button color to #0025ae
    }
}
