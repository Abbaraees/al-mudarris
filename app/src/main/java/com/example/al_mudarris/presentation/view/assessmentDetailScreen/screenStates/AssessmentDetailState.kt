package com.example.al_mudarris.presentation.view.assessmentDetailScreen.screenStates

import com.example.al_mudarris.database.entites.Assessment
import com.example.al_mudarris.database.entites.ScoreWithAssessmentAndStudent

data class AssessmentDetailState(
    val assessment: Assessment? = null,
    val title: String = "",
    val releaseDate: String = "",
    val dueDate: String = "",
    val description: String = "",
    val averageScore: Double = 0.0,
    val studentScores: List<ScoreWithAssessmentAndStudent> = emptyList(),
    val isEditing: Boolean = false,
    val showDeleteDialog: Boolean = false
)
