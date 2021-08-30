package com.arttttt.dictionary.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface WordsApi {
    @GET("v2/entries/en/{word}")
    suspend fun getWord(@Path("word") word: String): Any
}
