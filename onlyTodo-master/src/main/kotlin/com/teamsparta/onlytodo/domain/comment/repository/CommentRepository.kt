package com.teamsparta.onlytodo.domain.comment.repository

import com.teamsparta.onlytodo.domain.comment.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<CommentEntity, Long> {

}