package com.dhruv.firebasepractice.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    modifier: Modifier = Modifier,
    title: String? = "",
    icon: ImageVector? = null,
    onIconClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title!!)
        },
        navigationIcon = {
            IconButton(onClick = {
                onIconClick()
            }) {
                Icon(imageVector = icon!!, contentDescription = null)
            }
        }
    )
}