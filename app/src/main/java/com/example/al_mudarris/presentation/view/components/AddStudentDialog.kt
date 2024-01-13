package com.example.al_mudarris.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.al_mudarris.presentation.view.studentsScreen.screenEvents.StudentsEvent
import com.example.al_mudarris.presentation.view.studentsScreen.screenStates.StudentsState
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentDialog(
    state: State<StudentsState>,
    onEvent: (StudentsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var dropDownExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(StudentsEvent.HideDialog) },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    onEvent(StudentsEvent.SaveStudent)
                    onEvent(StudentsEvent.SetName(""))
                    onEvent(StudentsEvent.SetDob(""))
                    onEvent(StudentsEvent.SetGender(""))
                    onEvent(StudentsEvent.SetEmergencyContact(""))
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
                Text("Add Student")
            }
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = state.value.name,
                    onValueChange = { onEvent(StudentsEvent.SetName(it)) },
                    placeholder = {
                        Text(text = "Name")
                    }
                )

                ExposedDropdownMenuBox(
                    expanded = dropDownExpanded,
                    onExpandedChange = {dropDownExpanded = !dropDownExpanded})
                {
                    TextField(
                        value = if (state.value.gender.isBlank()) "Select Gender" else state.value.gender,
                        onValueChange = {} ,
                        readOnly = true,
                        trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownExpanded)},
                        modifier = Modifier.menuAnchor()
                    )
                    DropdownMenu(
                        expanded = dropDownExpanded,
                        onDismissRequest = { dropDownExpanded = !dropDownExpanded})
                    {
                        DropdownMenuItem(
                            text = { Text(text = "Male") },
                            onClick = {
                                onEvent(StudentsEvent.SetGender("Male"))
                                dropDownExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Female") },
                            onClick = {
                                onEvent(StudentsEvent.SetGender("Female"))
                                dropDownExpanded = false
                            })
                    }
                }

                TextField(
                    value = state.value.dob,
                    onValueChange = { onEvent(StudentsEvent.SetDob(it)) },
                    placeholder = {
                        Text(text = "Date of Birth")
                    }
                )

                TextField(
                    value = state.value.emergencyContact,
                    onValueChange = { onEvent(StudentsEvent.SetEmergencyContact(it)) },
                    placeholder = {
                        Text(text = "Emergency Contact")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    )
                )
            }
        },
    )
}
