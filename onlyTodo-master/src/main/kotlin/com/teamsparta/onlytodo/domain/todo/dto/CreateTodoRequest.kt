package com.teamsparta.onlytodo.domain.todo.dto

data class CreateTodoRequest(
    val category: String,
    val title: String,
    val content: String
)