package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class AllBooksScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(Modifier.fillMaxWidth().padding(all=16.dp)) {
            ClickableText(
                text = AnnotatedString("< BACK"),
                onClick = {
                    navigator.pop()
                },
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            BookUI().allBooksItem()
        }
    }
}





