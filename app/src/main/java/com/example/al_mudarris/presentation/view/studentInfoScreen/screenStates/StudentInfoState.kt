package com.example.al_mudarris.presentation.view.studentInfoScreen.screenStates

data class StudentInfoState(
    val id: Int = 0,
    val name: String = "",
    val gender: String = "",
    val dob: String = "",
    val presents: Int = 0,
    val absents: Int = 0,
    val averageScores: Double = 0.0,
    val comment: String = "",
    val showDeleteDialog: Boolean = false,
)
