package com.dhruv.firebasepractice.presentation.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dhruv.firebasepractice.presentation.component.NavigationDrawerComponent
import com.dhruv.firebasepractice.presentation.component.TopAppBarComponent
import com.dhruv.firebasepractice.presentation.viewmodel.CommonViewModel
import com.dhruv.firebasepractice.presentation.viewmodel.TodoViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlin.system.exitProcess

@Serializable
object HomeS
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: CommonViewModel,
    nvm: TodoViewModel,
) {
    val isLoading by vm.isLoading.collectAsState()
    val userDetail by vm.userDetail.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Fetch user details when HomeScreen is displayed
    LaunchedEffect(Unit) {
        vm.getUserDetails()
    }

    BackHandler {
        exitProcess(0)
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerComponent()
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBarComponent(
                    title = "Home",
                    icon = Icons.Default.Menu,
                    onIconClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        ) {
            Box(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    userDetail?.let {
                        Text(text = "Welcome, ${it.name}!")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        vm.logout()
                        navController.navigate(LoginS)
                    }) {
                        Text(text = "Logout")
                    }
                }
            }
        }
    }
}
