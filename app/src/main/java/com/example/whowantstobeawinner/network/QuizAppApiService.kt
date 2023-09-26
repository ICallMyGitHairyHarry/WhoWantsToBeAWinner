package com.example.whowantstobeawinner.network

import com.example.whowantstobeawinner.model.Quiz
import com.example.whowantstobeawinner.model.QuizQuestion
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface QuizAppApiService {

    @GET("photos")
    suspend fun getQuizes(): List<Quiz>

    /*@GET("quiz/{id}")
    suspend fun getQuizQuestions(@Path("id") id: Int): List<QuizQuestion>*/

    @GET("question/{id}") // @GET("quiz/{quizId}/question/{questionId}")
    suspend fun getQuestion(@Path("id") id: Int): QuizQuestion

}

object QuizAppApi {
    val retrofitService : QuizAppApiService by lazy {
        retrofit.create(QuizAppApiService::class.java) }
}