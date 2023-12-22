package com.example.test.domain.model

data class InfoListState(
    val isLoading: Boolean = false,
    val tasks: List<String> = emptyList(),
    val error: String = "",
    val isEmpty: Boolean = false
)
