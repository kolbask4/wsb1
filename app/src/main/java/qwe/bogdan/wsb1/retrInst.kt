package qwe.bogdan.wsb1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CommentsRetrInst {
    val api: CommentApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommentApiService::class.java)
    }
}

object PostsRetrInst {
    val api: PostApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApiService::class.java)
    }
}

object ChatsRetrInst {
    val api: ChatApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApiService::class.java)
    }
}