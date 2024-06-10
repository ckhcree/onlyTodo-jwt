package com.teamsparta.onlytodo.domain.todo.service

import com.teamsparta.onlytodo.domain.exception.ModelNotFoundException
import com.teamsparta.onlytodo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.onlytodo.domain.todo.dto.TodoResponse
import com.teamsparta.onlytodo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.onlytodo.domain.todo.model.TodoEntity
import com.teamsparta.onlytodo.domain.todo.model.toResponse
import com.teamsparta.onlytodo.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class TodoServiceImpl(private val todoRepository: TodoRepository) : TodoService {

    override fun getAllTodoList(): List<TodoResponse> {
        return todoRepository.findAll().map{ it.toResponse()}
    }

    override fun getTodoById(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", "todoId")
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(todo: CreateTodoRequest): TodoResponse {

        return todoRepository.save(
            TodoEntity(
                title = todo.title,
                content = todo.content,
                category = todo.category
            )).toResponse()
    }

    @Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId)
        ?: throw ModelNotFoundException("Todo", "todoId")
        todo.title = request.title
        todo.content = request.content
        todo.category = request.category
        todo.status = request.status
        return todo.toResponse()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", "todoId")
        todoRepository.delete(todo)
    }
}