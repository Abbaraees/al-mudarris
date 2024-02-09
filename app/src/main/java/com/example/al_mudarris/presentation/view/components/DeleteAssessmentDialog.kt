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
import com.example.al_mudarris.navigations.AssessmentDest
import com.example.al_mudarris.navigations.Students
import com.example.al_mudarris.presentation.view.assessmentDetailScreen.screenEvents.AssessmentDetailEvent
import com.example.al_mudarris.presentation.view.assessmentDetailScreen.screenStates.AssessmentDetailState
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenEvents.StudentInfoEvent
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenStates.StudentInfoState

@Composable
fun DeleteAssessmentDialog(
    navController: NavHostController,
    state: State<AssessmentDetailState>,
    onEvent: (AssessmentDetailEvent) -> Unit,
    ) {
    AlertDialog(
        onDismissRequest = { onEvent(AssessmentDetailEvent.HideDeleteAssessmentDialog) },
        confirmButton = {
            Row {
                Button(
                    onClick = {
                        onEvent(AssessmentDetailEvent.DeleteAssessment)
                        navController.popBackStack(AssessmentDest.route, false)
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "Yes")
                }
                Button(
                    onClick = { onEvent(AssessmentDetailEvent.HideDeleteAssessmentDialog) }
                ) {
                    Text(text = "No")
                }
            }
        },
        title = {
            Text("Delete Assessment")
        },
        text = {
            Column {
                Text(text = "Are you sure you want to delete ")
                Text(state.value.title, fontWeight = FontWeight.Bold)
            }

        }
    )
}