package com.dhruv.firebasepractice.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dhruv.firebasepractice.presentation.component.CommonProgressBar
import com.dhruv.firebasepractice.presentation.viewmodel.CommonViewModel
import kotlinx.serialization.Serializable

@Serializable
object InformationS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: CommonViewModel,

    ) {
    val userDetail by vm.userDetail.collectAsState()
    val isLoading by vm.isLoading.collectAsState()
    val errorMsg by vm.errorMsg.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("User Details") })
        }
    ) {
        Box(
            modifier = modifier.padding(it), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (userDetail != null) {
                    Text("Name: ${userDetail!!.name}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Number: ${userDetail!!.number}")
                } else {
                    OutlinedTextField(
                        value = vm.name.collectAsState().value,
                        onValueChange = { vm.updateName(it) },
                        label = { Text("Name") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = vm.number.collectAsState().value,
                        onValueChange = { vm.updateNumber(it) },
                        label = { Text("Number") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        vm.saveUserDetails(
                            onSuccess = {
                                navController.navigate(HomeS)
                            }
                        )
                    }, enabled = !isLoading) {
                        Text("Save Details")
                    }
                    if (isLoading) {
                        CommonProgressBar()
                    }
                    if (errorMsg.isNotEmpty()) {
                        Text(text = errorMsg, color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}