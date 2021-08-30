package com.arttttt.dictionary.data.repository

import com.arttttt.dictionary.data.api.WordsApi
import com.arttttt.dictionary.domain.entity.Word
import com.arttttt.dictionary.domain.repository.WordsRepository
import javax.inject.Inject

class WordsRepositoryImpl @Inject constructor(
    private val wordsApi: WordsApi
) : WordsRepository {
    override suspend fun getWord(word: String): Word {
        val response = wordsApi.getWord(word)

        return Word(
            meanings = emptyList(),
            phonetics = emptyList(),
            word = word
        )
    }
}
