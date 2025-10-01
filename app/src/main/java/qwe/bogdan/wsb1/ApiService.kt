package qwe.bogdan.wsb1

import retrofit2.http.GET

interface CommentApiService {
    @GET("comments")
    suspend fun getComments(): List<Comments>
}

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<Posts>
}

interface ChatApiService {
    @GET("todos")
    suspend fun getChats(): List<Chats>
}