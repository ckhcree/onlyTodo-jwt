package com.teamsparta.onlytodo.domain.todo.dto

import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    var createdtime: LocalDateTime,
    var status: Boolean,
    val category: String
)