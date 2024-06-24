package com.dhruv.firebasepractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.dhruv.firebasepractice.data.repository.Todo.TodoInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val todo : TodoInterface
): ViewModel() {



}