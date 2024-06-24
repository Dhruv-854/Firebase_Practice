package com.dhruv.firebasepractice.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NavigationDrawerComponent(modifier: Modifier = Modifier) {
    val scroll = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scroll)
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Red)
        ) {

        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Blue)
        ) {
            val items = listOf(
                NavigationDrawerItemData(title = "Home", icon = Icons.Default.Home),
                NavigationDrawerItemData(title = "Profile", icon = Icons.Default.Person),
                NavigationDrawerItemData(title = "Settings", icon = Icons.Default.Settings)
            )
            LazyColumn(
                modifier = modifier.fillMaxWidth()
            ) {
                items(items){item->
                    NavigationDrawerItem(title = item.title , icon = item.icon)
                }
            }
        }
    }
}
data class NavigationDrawerItemData(
    val title: String,
    val icon: ImageVector
)

@Composable
fun NavigationDrawerItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = modifier.width(16.dp))
        Text(text = title)
    }
}


