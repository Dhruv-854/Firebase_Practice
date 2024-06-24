package com.dhruv.firebasepractice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dhruv.firebasepractice.presentation.screen.HomeS
import com.dhruv.firebasepractice.presentation.screen.HomeScreen
import com.dhruv.firebasepractice.presentation.screen.InformationS
import com.dhruv.firebasepractice.presentation.screen.InformationScreen
import com.dhruv.firebasepractice.presentation.screen.LoginS
import com.dhruv.firebasepractice.presentation.screen.LoginScreen
import com.dhruv.firebasepractice.presentation.screen.SignupS
import com.dhruv.firebasepractice.presentation.screen.SignupScreen
import com.dhruv.firebasepractice.presentation.viewmodel.CommonViewModel
import com.dhruv.firebasepractice.presentation.viewmodel.TodoViewModel

@Composable
fun NavigationController(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val vm = hiltViewModel<CommonViewModel>()
    val nvm = hiltViewModel<TodoViewModel>()
    val isLoggedIn = vm.isLoggedIn.collectAsState()
    NavHost(
        navController = navController, startDestination = if (isLoggedIn.value) HomeS else SignupS
    ) {
        composable<SignupS> { SignupScreen(navController = navController, vm = vm) }
        composable<LoginS> { LoginScreen(navController = navController, vm = vm) }
        composable<InformationS> { InformationScreen(navController = navController , vm = vm) }
        composable<HomeS> { HomeScreen(navController = navController, vm = vm , nvm = nvm) }
    }
}