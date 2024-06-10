package com.teamsparta.onlytodo.domain.todo.repository

import com.teamsparta.onlytodo.domain.todo.model.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<TodoEntity, Long> {

}