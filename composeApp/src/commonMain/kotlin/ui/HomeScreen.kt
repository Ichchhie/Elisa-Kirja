package ui
import Greeting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import wasmdemo.composeapp.generated.resources.Res
import wasmdemo.composeapp.generated.resources.audiobook
import wasmdemo.composeapp.generated.resources.elisa

@OptIn(ExperimentalResourceApi::class)
class HomeScreen: Screen {

    @Composable
    override fun Content() {
        var showContent by remember { mutableStateOf(true) }
        // changed Column to LazyColumn for vertical scrolling of the page
        LazyColumn(Modifier.fillMaxHeight()) {
            item {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                    AnimatedVisibility(showContent) {
                        val greeting = remember { Greeting().greet() }
                        Row(Modifier.fillMaxWidth().background(color = Color.Blue).padding(horizontal = 42.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(0.6F, fill = true).padding(42.dp), horizontalAlignment = Alignment.Start) {
                                Text(
                                    "$greeting",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 64.sp,
                                    color = Color.White,
                                    lineHeight = 42.sp,
                                )
                                Spacer(Modifier.height(24.dp))
                                Text(
                                    Greeting().elisaDescription(),
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.W500,
                                    lineHeight = 32.sp,
                                )
                                Spacer(Modifier.height(24.dp))
                                Button(onClick = {  },colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                                    Text(Greeting().browseBooks(),
                                        color = Color.Blue,)
                                }
                            }
                            Column(Modifier.weight(0.4F, fill = true), horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(painterResource(Res.drawable.audiobook), null)
                            }
                        }
                    }
                }
            }
            items(Greeting().getBookCategories()) { product ->
                BookUI().categoryItem(product)
            }
        }
    }

}





