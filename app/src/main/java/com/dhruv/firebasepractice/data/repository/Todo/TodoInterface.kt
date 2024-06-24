package com.dhruv.firebasepractice.data.repository.Todo

import com.dhruv.firebasepractice.data.model.TodoData
import kotlinx.coroutines.flow.Flow

interface TodoInterface {

    suspend fun createTodo() : Flow<TodoData>

    suspend fun getTodo() : Flow<List<TodoData>>

    suspend fun updateTodo() : Flow<TodoData>

    suspend fun deleteTodo() : Flow<TodoData>

}