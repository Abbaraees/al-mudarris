package com.example.al_mudarris.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.al_mudarris.presentation.view.assessmentScreen.screenEvents.AssessmentEvent
import com.example.al_mudarris.presentation.view.assessmentScreen.screenStates.AssessmentState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAssessmentDialog(
    state: State<AssessmentState>,
    onEvent: (AssessmentEvent) -> Unit,
    modifier: Modifier = Modifier) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(AssessmentEvent.HideAddAssessmentDialog) },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    onEvent(AssessmentEvent.AddAssessment)
                    onEvent(AssessmentEvent.ResetInputs)
                }) {
                    Text(text = "Add")
                }
            }
        },
        title = {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Assessment")
            }
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = state.value.title,
                    onValueChange = { onEvent(AssessmentEvent.SetTitle(it)) },
                    placeholder = {
                        Text(text = "Title")
                    }
                )

                TextField(
                    value = state.value.description,
                    onValueChange = { onEvent(AssessmentEvent.SetDescription(it)) },
                    placeholder = {
                        Text(text = "Description")
                    }
                )


                TextField(
                    value = state.value.releaseDate,
                    onValueChange = { onEvent(AssessmentEvent.SetReleaseDate(it)) },
                    placeholder = {
                        Text(text = "Release Date")
                    }
                )

                TextField(
                    value = state.value.dueDate,
                    onValueChange = { onEvent(AssessmentEvent.SetDueDate(it)) },
                    placeholder = {
                        Text(text = "Due Date")
                    }
                )


            }
        },
    )
}