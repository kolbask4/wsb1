package qwe.bogdan.wsb1

import android.text.Layout
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import qwe.bogdan.wsb1.ui.theme.BackBlack
import qwe.bogdan.wsb1.ui.theme.GradientGreen1
import qwe.bogdan.wsb1.ui.theme.NavBlack
import kotlin.random.Random

@Composable
fun ChatScreen(
    onNavigate: () -> Unit,
    perepiska: MutableList<String>,
    viewModel: ChatViewModel = ChatViewModel(),
    ttfOutsideValue: MutableState<String>
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Scaffold(
        containerColor = BackBlack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                        .clickable {
                            perepiska.clear()
                            perepiska.add("Привет!")
                            ttfOutsideValue.value = ""
                            onNavigate()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Rounded.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                Text(
                    "Чат", color = Color.White
                )
                Box(
                    modifier = Modifier
                        .size(48.dp)
                )
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                itemsIndexed(perepiska) { index, message ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if ((index % 2) == 0) {
                                    Color(0xFF1A4D3C)
                                } else {
                                    Color(0xFF1F1F21)
                                }
                            )
                            .padding(16.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if ((index % 2) == 0) {
                                            R.drawable.bot_avatar
                                        } else {
                                            R.drawable.user_avatar
                                        }
                                    ), contentDescription = "", tint = Color.Green
                                )
                            }
                            Text(message, color = Color.White)
                        }
                        Spacer(Modifier.height(8.dp))
                        if (index % 2 == 0 && index != 0) {
                            Box(
                                modifier = Modifier
                                    .background(Color.White.copy(alpha = 0.2f), CircleShape)
                                    .clickable(
                                        onClick = {
                                            clipboardManager.setText(
                                                annotatedString = AnnotatedString(
                                                    message
                                                )
                                            )
                                            Toast.makeText(
                                                context,
                                                "Скопировано",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    )
                            ) {
                                Text(
                                    "Копировать", color = Color.White, modifier = Modifier
                                        .padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = ttfOutsideValue.value,
                    onValueChange = { ttfOutsideValue.value = it },
                    placeholder = { Text("Введите текст", color = Color.Gray) },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFF1E1F21),
                        focusedContainerColor = Color(0xFF1E1F21),
                        unfocusedIndicatorColor = Color(0xFF1E1F21),
                        focusedIndicatorColor = Color(0xFF1E1F21),
                        errorContainerColor = Color(0xFF1E1F21),
                        errorIndicatorColor = Color(0xFF1E1F21),
                        focusedTextColor = Color(0xFFFFFFFF),
                        unfocusedTextColor = Color(0xFFFFFFFF),
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(Modifier.width(8.dp))
                if (ttfOutsideValue.value != "") {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                perepiska.add(ttfOutsideValue.value)
                                perepiska.add(LoremIpsum(Random.nextInt(20, 89)).values.first())
                                ttfOutsideValue.value = ""
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Rounded.PlayArrow,
                            contentDescription = "",
                            tint = Color.Green,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                }
            }
        }
    }
}