package com.example.al_mudarris.presentation.view.studentInfoScreen.screenStates

import com.example.al_mudarris.database.entites.ScoreWithAssessmentAndStudent

data class StudentInfoState(
    val id: Int = 0,
    val name: String = "",
    val gender: String = "",
    val dob: String = "",
    val presents: Int = 0,
    val absents: Int = 0,
    val averageScores: Double = 0.0,
    val comment: String = "",
    val emergencyContact: String = "",
    val showDeleteDialog: Boolean = false,
    val showUpdateDialog: Boolean = false,
    val assessments: List<ScoreWithAssessmentAndStudent> = listOf()
)
