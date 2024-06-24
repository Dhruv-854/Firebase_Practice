package com.dhruv.firebasepractice.data.repository.Todo

import com.dhruv.firebasepractice.TO_DO
import com.dhruv.firebasepractice.USER
import com.dhruv.firebasepractice.data.model.TodoData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : TodoInterface {

    private val userId =auth.currentUser?.uid?:""
    val newTodo = TodoData(id = db.collection(USER).document(userId).collection(TO_DO).document().id)
    override suspend fun createTodo(): Flow<TodoData> = flow {
        db.collection(USER).document(userId).collection(TO_DO).document(newTodo.id!!).set(newTodo)
        emit(newTodo)
    }

    override suspend fun getTodo(): Flow<List<TodoData>> = flow {

    }

    override suspend fun updateTodo(): Flow<TodoData> = flow{

    }

    override suspend fun deleteTodo(): Flow<TodoData>  = flow{

    }
}