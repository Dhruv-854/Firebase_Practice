package com.dhruv.firebasepractice.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.dhruv.firebasepractice.presentation.component.CommonProgressBar
import com.dhruv.firebasepractice.presentation.viewmodel.CommonViewModel
import kotlinx.serialization.Serializable
import kotlin.system.exitProcess

@Serializable
object SignupS

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: CommonViewModel,
) {
    val email by vm.email.collectAsState()
    val password by vm.password.collectAsState()
    val isLoading by vm.isLoading.collectAsState()
    val error by vm.errorMsg.collectAsState()
    BackHandler {
        exitProcess(0)
    }
    Scaffold {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Column(
                modifier = modifier.fillMaxSize()
            ) {

                if (isLoading) {
                    CommonProgressBar()
                } else {
                    SignupComponent(
                        email = email,
                        password = password,
                        onEmailChange = {
                            vm.updateEmail(it)
                        },
                        onPasswordChange = {
                            vm.updatePassword(it)
                        },
                        onSignup = {
                            vm.SingUp(
                                onSuccess = {

                                    navController.navigate(LoginS)

                                }
                            )
                        },
                        onClick = {
                            navController.navigate(LoginS)
                        }
                    )

                }
                if (error.isNotEmpty()) {
                    Text(text = error, color = Color.Red)
                }

            }
        }
    }
}

@Composable
fun SignupComponent(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignup: () -> Unit,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = email, onValueChange = onEmailChange)
        OutlinedTextField(value = password, onValueChange = onPasswordChange)
        OutlinedButton(onClick = {
            onSignup()
        }) {
            Text(text = "SingUp")
        }
        Text(text = "Already have an account? Login", modifier = modifier.clickable { onClick() })

    }
}
