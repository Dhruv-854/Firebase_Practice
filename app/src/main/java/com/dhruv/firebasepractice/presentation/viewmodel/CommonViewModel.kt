package com.dhruv.firebasepractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhruv.firebasepractice.USER
import com.dhruv.firebasepractice.data.model.UserDetail
import com.dhruv.firebasepractice.data.repository.User.UserDataInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class CommonViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val userData: UserDataInterface,
    private val db: FirebaseFirestore,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg: StateFlow<String> = _errorMsg.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _userDetail = MutableStateFlow<UserDetail?>(null)
    val userDetail: StateFlow<UserDetail?> = _userDetail.asStateFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _number = MutableStateFlow("")
    val number: StateFlow<String> = _number.asStateFlow()
    val userId = auth.currentUser?.uid ?: ""


    init {
        auth.currentUser?.let {
            _isLoggedIn.value = true
        }
    }

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun updateName(name: String) {
        _name.value = name
    }

    fun updateNumber(number: String) {
        _number.value = number
    }


    fun SingUp(onSuccess: () -> Unit) {
        checknull()
        _isLoading.value = true
        viewModelScope.launch {
            try {
                userData.Signup(email.value, password.value)
                _isLoading.value = true
                onSuccess()
            } catch (e: Exception) {
                _errorMsg.value = e.message ?: "Unknown Error!!"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun login(onSuccess: () -> Unit, onUserDetailExist: () -> Unit) {
        checknull()
        _isLoading.value = true
        viewModelScope.launch {
            try {
                userData.Login(email.value, password.value)
                _isLoading.value = true
                userExists(
                    onSuccess = {
                        getUserDetails()
                        onSuccess()
                    },
                    onUserDetailExist = {
                        getUserDetails()
                        onUserDetailExist()
                    }
                )
            } catch (e: Exception) {
                _errorMsg.value = e.message ?: "Unknown Error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        auth.signOut()
        _isLoggedIn.value = false
    }

    fun checknull() {
        if (email.value.isEmpty() || password.value.isEmpty()) {
            _errorMsg.value = "Please fill all the fields"
            return
        }
    }

    fun getUserDetails() {
        viewModelScope.launch {
            val userDocRef = db.collection(USER).document(userId).get().await()
            if (userDocRef.exists()) {
                _userDetail.value = userDocRef.toObject(UserDetail::class.java)
            } else {
                _userDetail.value = null
            }
        }
    }

    fun saveUserDetails(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val userDetail = UserDetail(_name.value, _number.value)
                userData.insertUserData(userDetail).collect {
                    onSuccess()
                }
            } catch (e: Exception) {
                _errorMsg.value = e.message ?: "Unknown Error!!"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun userExists(onUserDetailExist: () -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val docref = db.collection(USER).document(userId).get().await()
            if (docref.exists()) {
                _userDetail.value = docref.toObject(UserDetail::class.java)
                onUserDetailExist()
            } else {
                _userDetail.value = null
                onSuccess()
            }
        }
    }
}