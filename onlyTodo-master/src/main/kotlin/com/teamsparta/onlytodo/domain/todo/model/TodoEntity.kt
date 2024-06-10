package com.teamsparta.onlytodo.domain.todo.model

import com.teamsparta.onlytodo.domain.comment.model.CommentEntity
import com.teamsparta.onlytodo.domain.todo.dto.TodoResponse
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "todo")
class TodoEntity (

    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "createdtime")
    var createdtime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "category")
    var category: String,

    @Column(name = "status")
    var status: Boolean = false,

    @OneToMany(mappedBy = "todo", cascade = [(CascadeType.ALL)],orphanRemoval = true,fetch = FetchType.LAZY)
    var comment: MutableList<CommentEntity> = mutableListOf(),

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun TodoEntity.toResponse()
        : TodoResponse {
    return TodoResponse(

        id = id!!,
        title = title,
        content = content,
        createdtime = createdtime,
        category = category,
        status = status,
    )
}