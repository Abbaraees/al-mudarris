package com.example.al_mudarris.presentation.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.al_mudarris.navigations.Students
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenEvents.StudentInfoEvent
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenStates.StudentInfoState

@Composable
fun DeleteStudentDialog(
    navController: NavHostController,
    state: State<StudentInfoState>,
    onEvent: (StudentInfoEvent) -> Unit,

    ) {
    AlertDialog(
        onDismissRequest = { onEvent(StudentInfoEvent.HideDeleteDialog) },
        confirmButton = {
            Row {
                Button(
                    onClick = {
                        onEvent(StudentInfoEvent.DeleteStudent)
                        onEvent(StudentInfoEvent.HideDeleteDialog)
                        navController.popBackStack(Students.route, false)
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "Yes")
                }
                Button(
                    onClick = { onEvent(StudentInfoEvent.HideDeleteDialog) }
                ) {
                    Text(text = "No")
                }
            }
        },
        title = {
            Text("Delete Student")
        },
        text = {
            Column {
                Text(text = "Are you sure you want to delete ")
                Text(state.value.name, fontWeight = FontWeight.Bold)
            }

        }
    )
}