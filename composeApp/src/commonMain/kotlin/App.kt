import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.AppTheme
import ui.HomeScreen
import ui.NavBar

@Composable
@Preview
fun App() {
    val isDarkTheme = rememberSaveable { mutableStateOf(false) }

    AppTheme(isDarkTheme = isDarkTheme.value, toggleTheme = { isDarkTheme.value = !isDarkTheme.value }) {
        Column {
            NavBar().DisplayNavBar(isDarkTheme = isDarkTheme.value, toggleTheme = { isDarkTheme.value = !isDarkTheme.value })
            Navigator(screen = HomeScreen(isDarkTheme = isDarkTheme.value, toggleTheme = { isDarkTheme.value = !isDarkTheme.value }))
        }
    }
}
