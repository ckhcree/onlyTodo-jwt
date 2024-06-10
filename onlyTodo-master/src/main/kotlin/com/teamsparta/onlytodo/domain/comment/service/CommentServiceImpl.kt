package com.teamsparta.onlytodo.domain.comment.service

import com.teamsparta.onlytodo.domain.comment.dto.CommentResponse
import com.teamsparta.onlytodo.domain.comment.dto.CreateCommentRequest
import com.teamsparta.onlytodo.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.onlytodo.domain.comment.model.CommentEntity
import com.teamsparta.onlytodo.domain.comment.model.toResponse
import com.teamsparta.onlytodo.domain.comment.repository.CommentRepository
import com.teamsparta.onlytodo.domain.exception.ModelNotFoundException
import com.teamsparta.onlytodo.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository) : CommentService {

    override fun getCommentById(commentId: Long): CommentResponse{
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", "commentId")
        return comment.toResponse()
    }

    @Transactional
    override fun createComment(comment: CreateCommentRequest): CommentResponse {
        val forcomment = todoRepository.findByIdOrNull(comment.todoId)
            ?: throw ModelNotFoundException("Comment", "todoId")
        return commentRepository.save(
            CommentEntity(
                password = comment.password,
                content = comment.content,
                todo = forcomment,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", "commentId")
        comment.content = request.content
        return comment.toResponse()
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", "commentId")
        commentRepository.delete(comment)
    }
}