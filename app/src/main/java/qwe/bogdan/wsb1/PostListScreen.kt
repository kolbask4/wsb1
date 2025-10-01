package qwe.bogdan.wsb1

import android.bluetooth.BluetoothA2dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qwe.bogdan.wsb1.ui.theme.GradientBlue1
import qwe.bogdan.wsb1.ui.theme.GradientBlue2
import qwe.bogdan.wsb1.ui.theme.GradientGreen1
import qwe.bogdan.wsb1.ui.theme.GradientGreen2
import qwe.bogdan.wsb1.ui.theme.GradientOrange1
import qwe.bogdan.wsb1.ui.theme.GradientOrange2
import qwe.bogdan.wsb1.ui.theme.GradientPurple1
import qwe.bogdan.wsb1.ui.theme.GradientPurple2
import kotlin.random.Random

@Composable
fun PostListScreen(
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = PostViewModel(),
    perepiska: MutableList<String>,
    onNavigate: () -> Unit,
    chatHistoryQ: MutableList<String>,
    chatHistoryA: MutableList<String>,
    ttfOutsideValue: MutableState<String>
) {
    val coms by viewModel.coms
    val posts by viewModel.posts

    if (coms.isEmpty() && posts.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier
                .padding(16.dp, 0.dp, 16.dp, 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                )
                Text(
                    "Главная", color = Color.White
                )
                Box(
                    modifier = Modifier
                        .size(48.dp)
                )
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    "История",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )
                Spacer(Modifier.height(16.dp))
                LazyRow {
                    itemsIndexed(chatHistoryQ) { index, question ->
                        Column(
                            modifier = Modifier
                                .padding(
                                    start = (if (index == 0) {
                                        0.dp
                                    } else {
                                        16.dp
                                    })
                                )
                                .width(300.dp)
                                .background(Color(0xFF1E1F21), shape = RoundedCornerShape(16.dp))
                                .clickable {
                                    perepiska.add(question)
                                    perepiska.add(chatHistoryA[index])
                                    onNavigate()
                                }
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                            ) {
                                Text(
                                    question,
                                    modifier = Modifier,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Box(
                                    modifier = Modifier
                                        .padding(top = 8.dp, bottom = 8.dp)
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(
                                            Color.DarkGray,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                )
                                Text(
                                    chatHistoryA[index],
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(Modifier.height(16.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "13 июня 2033",
                                        color = Color.Gray
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(4.dp)
                                            .background(Color(0xFF52D1A2), shape = CircleShape)
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        "14:88",
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.height(32.dp))
                Text(
                    "Категории",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Spacer(Modifier.height(16.dp))
                Text("💪🏻 Здоровье", color = Color.LightGray, fontSize = 20.sp)
                Spacer(Modifier.height(8.dp))
                LazyRow(
                ) {
                    itemsIndexed(coms) { index, com ->
                        PostCard(com, index, ttfOutsideValue = ttfOutsideValue, onNavigate = onNavigate)
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text("🔬 Science", color = Color.LightGray, fontSize = 20.sp)
                Spacer(Modifier.height(8.dp))
                LazyRow(
                ) {
                    itemsIndexed(posts) { index, post ->
                        ComCard(post, index, ttfOutsideValue = ttfOutsideValue, onNavigate = onNavigate)
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text("📚 Obrazovanie", color = Color.LightGray, fontSize = 20.sp)
                Spacer(Modifier.height(8.dp))
                LazyRow(
                ) {
                    itemsIndexed(posts) { index, post ->
                        ComCard(post, index, ttfOutsideValue = ttfOutsideValue, onNavigate = onNavigate)
                    }
                }
            }
        }
    }
}

@Composable
fun PostCard(comments: Comments, cardIndex: Int, ttfOutsideValue: MutableState<String>, onNavigate: () -> Unit) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(125.dp)
            .padding(
                start = (if (comments.id == "1") {
                    0.dp
                } else {
                    16.dp
                })
            )
            .clip(RoundedCornerShape(16.dp))
            .background(
                when (cardIndex % 4) {
                    0 -> {
                        Brush.linearGradient(listOf(GradientOrange1, GradientOrange2))
                    }

                    1 -> {
                        Brush.linearGradient(listOf(GradientPurple1, GradientPurple2))
                    }

                    2 -> {
                        Brush.linearGradient(listOf(GradientGreen1, GradientGreen2))
                    }

                    else -> {
                        Brush.linearGradient(colors = listOf(GradientBlue1, GradientBlue2))
                    }
                }
            )
            .clickable {
                ttfOutsideValue.value = comments.name
                onNavigate()
            }

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("Id: ${comments.id}", color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text(comments.name, color = Color.White)
        }
    }
}

@Composable
fun ComCard(posts: Posts, cardIndex: Int, ttfOutsideValue: MutableState<String>, onNavigate: () -> Unit) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(135.dp)
            .padding(
                start = (if (posts.id == "1") {
                    0.dp
                } else {
                    16.dp
                })
            )
            .clip(RoundedCornerShape(16.dp))
            .background(
                when (cardIndex % 4) {
                    0 -> {
                        Brush.linearGradient(colors = listOf(GradientBlue1, GradientBlue2))
                    }

                    1 -> {
                        Brush.linearGradient(listOf(GradientGreen1, GradientGreen2))
                    }

                    2 -> {
                        Brush.linearGradient(listOf(GradientOrange1, GradientOrange2))
                    }

                    else -> {
                        Brush.linearGradient(listOf(GradientPurple1, GradientPurple2))
                    }
                }
            )
            .clickable {
                ttfOutsideValue.value = posts.title
                onNavigate()
            }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                "Id: ${posts.id}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            Text(posts.title, color = Color.White)
        }
    }
}