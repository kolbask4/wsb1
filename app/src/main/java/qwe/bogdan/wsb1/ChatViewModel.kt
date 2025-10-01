package qwe.bogdan.wsb1

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    private val _chats = mutableStateOf<List<Chats>>(emptyList())
    val chats: State<List<Chats>> = _chats

    init {
        fetchChats()
    }

    private fun fetchChats() {
        viewModelScope.launch {
            try {
                val responseChat = ChatsRetrInst.api.getChats()
                _chats.value = responseChat
            } catch (e: Exception) {
                //barebux2002
            }
        }
    }
}
