package com.arttttt.dictionary.domain.repository

import com.arttttt.dictionary.domain.entity.Word

interface WordsRepository {
    suspend fun getWord(word: String): Word
}
