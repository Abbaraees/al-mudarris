package com.example.al_mudarris.presentation.view.studentsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.al_mudarris.presentation.view.components.AddStudentDialog
import com.example.al_mudarris.presentation.view.components.MyTopAppBar
import com.example.al_mudarris.presentation.view.studentsScreen.screenEvents.StudentsEvent
import com.example.al_mudarris.presentation.view.studentsScreen.screenStates.StudentsState
import com.example.al_mudarris.ui.theme.MyGreen
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsScreen(
    navController: NavHostController,
    state: State<StudentsState>,
    onEvent: KFunction1<StudentsEvent, Unit>
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            MyTopAppBar("Students", scrollBehaviour)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(StudentsEvent.ShowDialog) }) {
                Icon(imageVector = Icons.Default.PersonAdd, contentDescription = "", tint = MyGreen)
            }
        }
    ) {paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.value.isAdding) {
                AddStudentDialog(state, onEvent)
            }
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                OutlinedTextField(
                    value = state.value.searchValue,
                    onValueChange = {onEvent(StudentsEvent.SetSearchValue(it))},
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

                if (state.value.students.isEmpty()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            "No Students added yet!",
                            fontSize=20.sp,
                            color = MyGreen
                        )
                    }
                }
                else {
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        items(state.value.students) {student ->
                            Text(
                                text = student.name,
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
    
}


@Preview(showSystemUi = true)
@Composable
fun StudentsScreenPreview() {
//    StudentsScreen(
//        navController = rememberNavController(),
//        state = viewModel.state,
//        onEvent = viewModel::onEvent
//    )
}