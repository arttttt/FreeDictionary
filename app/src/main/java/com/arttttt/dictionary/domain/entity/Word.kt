package com.arttttt.dictionary.domain.entity

data class Word(
    val meanings: List<Meaning>,
    val phonetics: List<Phonetic>,
    val word: String
)
