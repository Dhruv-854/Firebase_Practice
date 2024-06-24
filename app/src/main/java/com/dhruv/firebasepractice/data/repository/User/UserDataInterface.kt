package com.dhruv.firebasepractice.data.repository.User


import com.dhruv.firebasepractice.data.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserDataInterface {
    suspend fun Signup (email : String , password:String)
    suspend fun Login(email: String , password: String)

    suspend fun insertUserData(userDetail: UserDetail) : Flow<String>

}