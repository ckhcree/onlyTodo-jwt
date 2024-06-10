package com.teamsparta.onlytodo.domain.comment.model

import com.teamsparta.onlytodo.domain.comment.dto.CommentResponse
import com.teamsparta.onlytodo.domain.todo.model.TodoEntity
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class CommentEntity (

    @Column(name = "password")
    var password: Int,

    @Column(name = "content")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoid")
    var todo : TodoEntity

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun CommentEntity.toResponse()
: CommentResponse {
    return CommentResponse(
        id = id!!,
        content = content
    )
}