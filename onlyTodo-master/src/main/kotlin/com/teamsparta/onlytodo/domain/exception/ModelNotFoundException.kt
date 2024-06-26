package com.teamsparta.onlytodo.domain.exception

data class ModelNotFoundException(val modelName: String, val id: String): RuntimeException(
    "Model $modelName not found with given id: $id"
)