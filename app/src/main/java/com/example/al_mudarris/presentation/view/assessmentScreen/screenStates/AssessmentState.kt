package com.example.al_mudarris.presentation.view.assessmentScreen.screenStates

import com.example.al_mudarris.database.entites.Assessment

data class AssessmentState(
    val assessments: List<Assessment> = listOf(),
    val title: String = "",
    val description: String = "",
    val releaseDate: String = "",
    val dueDate: String = "",
    val isAdding : Boolean = false,
)
