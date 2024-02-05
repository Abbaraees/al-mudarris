package com.example.al_mudarris.presentation.view.addAssessmentScoreScreen

import com.example.al_mudarris.database.entites.Assessment
import com.example.al_mudarris.database.entites.AssessmentScore
import com.example.al_mudarris.database.entites.Student

data class AddAssessmentScoreState(
    val assessment: Assessment = Assessment(0, "", "", "", ""),
    val studentScores: MutableList<AssessmentScore> = mutableListOf(),
    val students: List<Student> = listOf(),
)
