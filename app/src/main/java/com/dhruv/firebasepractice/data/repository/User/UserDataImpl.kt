package com.dhruv.firebasepractice.data.repository.User

import com.dhruv.firebasepractice.USER
import com.dhruv.firebasepractice.data.model.UserDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserDataImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : UserDataInterface {
    override suspend fun Signup(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun Login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun insertUserData(userDetail: UserDetail): Flow<String> = flow {
        val userId = auth.currentUser?.uid ?: ""
        db.collection(USER).document(userId).set(userDetail).await()
        emit("User details saved successfully")
    }


}