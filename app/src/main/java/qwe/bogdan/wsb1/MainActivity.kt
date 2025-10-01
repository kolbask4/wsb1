package qwe.bogdan.wsb1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qwe.bogdan.wsb1.ui.theme.BackBlack
import qwe.bogdan.wsb1.ui.theme.GradientBlue1
import qwe.bogdan.wsb1.ui.theme.NavBlack
import qwe.bogdan.wsb1.ui.theme.Wsb1Theme

sealed class Screen {
    data object FirstScreen : Screen()
    data object SecondScreen : Screen()
    data object ThirdScreen : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Wsb1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Black
                ) { innerPadding ->
                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.FirstScreen) }
    val showScaffold = currentScreen is Screen.FirstScreen || currentScreen is Screen.ThirdScreen

    val chatHistoryQ = remember {
        mutableStateListOf(
            "Какая самая длинная река в мире?",
            "Какая самая высокая гора в мире ахахахахаахаха?"
        )
    }

    val chatHistoryA = remember {
        mutableStateListOf(
            "Самая длинная река в мире - река нил, расположенная на Океании Индийского океана",
            "Самая высокая гора в мире - Эверест, высота которой достигает 8848 метров"
        )
    }

    val perepiska = remember {
        mutableStateListOf(
            "Привет!"
        )
    }

    var ttfOutsideValue = remember { mutableStateOf("") }

    if (showScaffold) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = BackBlack,
            bottomBar = {
                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(NavBlack),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { currentScreen = Screen.FirstScreen },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.grid),
                                contentDescription = "",
                                tint = if (currentScreen is Screen.FirstScreen) {
                                    Color(0xFF52D1A2)
                                } else {
                                    Color.Gray
                                },
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Text(
                                "Главная", color = if (currentScreen is Screen.FirstScreen) {
                                    Color(0xFF52D1A2)
                                } else {
                                    Color.Gray
                                }
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .offset(y = (-50).dp)
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(NavBlack)
                            .clickable {
                                currentScreen = Screen.SecondScreen
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .shadow(
                                    elevation = 4.dp,
                                    shape = CircleShape,
                                    ambientColor = Color(0xFF52D1A2),
                                    spotColor = Color(0xFF52D1A2)
                                )
                                .clip(CircleShape)
                                .background(Color(0xFF52D1A2)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.message),
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(48.dp)
                            )
                        }
                    }
                    IconButton(
                        onClick = { currentScreen = Screen.ThirdScreen },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.settings),
                                contentDescription = "",
                                tint = if (currentScreen is Screen.ThirdScreen) {
                                    Color(0xFF52D1A2)
                                } else {
                                    Color.Gray
                                },
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Text(
                                "Настройки", color = if (currentScreen is Screen.ThirdScreen) {
                                    Color(0xFF52D1A2)
                                } else {
                                    Color.Gray
                                }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            when (currentScreen) {
                is Screen.FirstScreen -> PostListScreen(
                    Modifier.padding(innerPadding),
                    perepiska = perepiska,
                    onNavigate = { currentScreen = Screen.SecondScreen },
                    chatHistoryQ = chatHistoryQ,
                    chatHistoryA = chatHistoryA,
                    ttfOutsideValue = ttfOutsideValue
                )

                is Screen.ThirdScreen -> SettingsScreen(Modifier.padding(innerPadding))
                else -> {}
            }
        }
    } else {
        when (currentScreen) {
            is Screen.SecondScreen -> ChatScreen(
                onNavigate = { currentScreen = Screen.FirstScreen },
                perepiska = perepiska,
                ttfOutsideValue = ttfOutsideValue
            )
            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Wsb1Theme {
        MainScreen(modifier = Modifier)
    }
}