package ui
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class AppStyles {
    companion object {
        val textColor = Color.Black
        val fontFamily = FontFamily.Default

        val primaryButtonColor = Color(0xFF0025AE) // Set primary button color to #0025ae

        val headingStyle = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = textColor
        )

        val h2Style = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = textColor
        )

        val h3Style = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = textColor
        )

        val h4Style = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = textColor
        )

        val h5Style = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = textColor
        )

        val h6Style = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = textColor
        )

        val subheadingStyle = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = textColor
        )

        val bodyStyle = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = textColor
        )

        val typography = Typography(
            body1 = bodyStyle,
            h1 = headingStyle,
            h2 = h2Style,
            h3 = h3Style,
            subtitle1 = subheadingStyle

        )


    }
}
