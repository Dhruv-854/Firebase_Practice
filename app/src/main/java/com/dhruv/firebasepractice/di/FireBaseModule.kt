package com.dhruv.firebasepractice.di

import com.dhruv.firebasepractice.data.repository.Todo.TodoImpl
import com.dhruv.firebasepractice.data.repository.Todo.TodoInterface
import com.dhruv.firebasepractice.data.repository.User.UserDataImpl
import com.dhruv.firebasepractice.data.repository.User.UserDataInterface
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FireBaseModule {
    companion object{
        @Provides
        fun providesAuthentication () : FirebaseAuth = Firebase.auth
        @Provides
        fun providesDatabase () : FirebaseFirestore = Firebase.firestore
    }
    @Binds
    abstract fun bindsUserData(userDataImpl: UserDataImpl) : UserDataInterface

    @Binds
    abstract fun bindsTodo(todoImpl: TodoImpl) : TodoInterface
}