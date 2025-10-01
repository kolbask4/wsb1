package qwe.bogdan.wsb1

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private val _coms = mutableStateOf<List<Comments>>(emptyList())
    val coms: State<List<Comments>> = _coms

    private val _posts = mutableStateOf<List<Posts>>(emptyList())
    val posts: State<List<Posts>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val responseCom = CommentsRetrInst.api.getComments()
                _coms.value = responseCom.take(3)

                val responsePos = PostsRetrInst.api.getPosts()
                _posts.value = responsePos.take(4)
            } catch (e: Exception) {
                //barebux2002
            }
        }
    }
}