package com.dhruv.firebasepractice.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dhruv.firebasepractice.presentation.component.CommonProgressBar
import com.dhruv.firebasepractice.presentation.viewmodel.CommonViewModel
import kotlinx.serialization.Serializable

@Serializable
object LoginS

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: CommonViewModel,
) {
    val email by vm.email.collectAsState()
    val password by vm.password.collectAsState()
    val isLoading by vm.isLoading.collectAsState()
    val error by vm.errorMsg.collectAsState()

    Scaffold {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier.fillMaxSize(),

            ) {
                if (isLoading) {
                    CommonProgressBar()

                }else{
                    LoginComponent(
                        email = email,
                        password = password,
                        onEmailChange = {
                            vm.updateEmail(it)
                        },
                        onPasswordChange = {
                            vm.updatePassword(it)
                        },
                        onLogin = {
                            vm.login(
                                onSuccess = {
                                    navController.navigate(InformationS)
                                },
                                onUserDetailExist = {
                                    navController.navigate(HomeS)
                                }
                            )
                        },
                        buttonText = "Login"
                    )
                    if (error.isNotEmpty()){
                        Text(text = error)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginComponent(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    buttonText : String,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = email, onValueChange = onEmailChange)
        OutlinedTextField(value = password, onValueChange = onPasswordChange)
        OutlinedButton(onClick = onLogin) {
            Text(text = buttonText)
        }
    }
}