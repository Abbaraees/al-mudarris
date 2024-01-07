package com.example.al_mudarris.presentation.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.example.al_mudarris.ui.theme.TopAppBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(title: String, scrollBehaviour: TopAppBarScrollBehavior? = null) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icon")
        },
        actions = {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "User Account")
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = TopAppBarColor),
        scrollBehavior = scrollBehaviour
    )
}