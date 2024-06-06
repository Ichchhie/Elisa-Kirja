import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.AppTheme
import ui.NavBar
import ui.TextAnimation
import ui.screens.HomeScreen
import ui.screens.NewHomeScreen

@Composable
@Preview
fun MyApp() {
    val isDarkTheme = rememberSaveable { mutableStateOf(false) }
    val splashVisible = remember { mutableStateOf(true) }

    AppTheme(
        isDarkTheme = isDarkTheme.value,
        toggleTheme = { isDarkTheme.value = !isDarkTheme.value }) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (splashVisible.value) {
                TextAnimation().SplashScreen {
                    splashVisible.value = false
                }
            } else {
                Box {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .background(MaterialTheme.colors.background)
                    ) {
                        NavBar().DisplayNavBar(
                            isDarkTheme = isDarkTheme.value,
                            toggleTheme = { isDarkTheme.value = !isDarkTheme.value })
                        Navigator(screen = HomeScreen())
                    }
                }
            }
        }
    }
}
