package com.example.al_mudarris.presentation.view.studentsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.al_mudarris.presentation.view.components.MyTopAppBar
import com.example.al_mudarris.ui.theme.MyGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsScreen(navController: NavHostController) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            MyTopAppBar("Students", scrollBehaviour)
        },


    ) {paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.PersonSearch, contentDescription = "search icon")
                    },
                    placeholder = {
                        Text(text = "Search for a student")
                    },
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .padding(8.dp)
                )

                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    items(50) {
                        Text(
                            text = "Muhammad Lawal",
                            color = MyGreen,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .clickable { }
                                .fillMaxWidth()
                        )
                        Divider(thickness = 1.dp, color = Color.DarkGray)
                    }
                }
            }
        }
    }
    
}


@Preview(showSystemUi = true)
@Composable
fun StudentsScreenPreview() {
    StudentsScreen(navController = rememberNavController())
}