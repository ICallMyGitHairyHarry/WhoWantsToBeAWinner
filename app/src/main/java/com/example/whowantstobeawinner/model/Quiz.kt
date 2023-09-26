package com.example.whowantstobeawinner.model

// might be extended with additional info (desc, category and so on)
// TODO: delete nullability ("?")
data class Quiz  (
    val id: Int,
    val name: String?,
    val questionsIDs: List<Int>?
)