package com.arttttt.dictionary.domain.entity

data class Definition(
    val definition: String,
    val example: String,
    val synonyms: List<String>
)
