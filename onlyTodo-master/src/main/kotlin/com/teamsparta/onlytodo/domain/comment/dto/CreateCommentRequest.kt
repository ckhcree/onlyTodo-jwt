package com.teamsparta.onlytodo.domain.comment.dto

class CreateCommentRequest (
    val password: Int,
    val content: String,
    val todoId: Long,
)