package com.teamsparta.onlytodo.domain.comment.service

import com.teamsparta.onlytodo.domain.comment.dto.CommentResponse
import com.teamsparta.onlytodo.domain.comment.dto.CreateCommentRequest
import com.teamsparta.onlytodo.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun getCommentById(commentId: Long): CommentResponse

    fun createComment(comment: CreateCommentRequest): CommentResponse

    fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentResponse

    fun deleteComment(commentId: Long)
}