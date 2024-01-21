package com.example.al_mudarris.presentation.view.assessmentScreen.screenEvents

sealed interface AssessmentEvent {
    object ShowAddAssessmentDialog: AssessmentEvent
    object HideAddAssessmentDialog: AssessmentEvent
    object AddAssessment: AssessmentEvent
    object ResetInputs: AssessmentEvent
    data class SetTitle(val title: String): AssessmentEvent
    data class SetDescription(val description: String): AssessmentEvent
    data class SetReleaseDate(val releaseDate: String): AssessmentEvent
    data class SetDueDate(val dueDate: String): AssessmentEvent
}