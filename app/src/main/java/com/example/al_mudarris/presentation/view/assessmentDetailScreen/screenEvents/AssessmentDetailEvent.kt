package com.example.al_mudarris.presentation.view.assessmentDetailScreen.screenEvents

sealed interface AssessmentDetailEvent {
    data class SetTitle(val title: String): AssessmentDetailEvent
    data class SetDescription(val description: String): AssessmentDetailEvent
    data class SetReleaseDate(val releaseDate: String): AssessmentDetailEvent
    data class SetDueDate(val dueDate: String): AssessmentDetailEvent
    data class SetAverageScore(val averageScore: Double): AssessmentDetailEvent
    data class LoadAssessmentDetail(val assessmentId: Int): AssessmentDetailEvent
    object ShowUpdateAssessmentDialog: AssessmentDetailEvent
    object HideUpdateAssessmentDialog: AssessmentDetailEvent
    object UpdateAssessment: AssessmentDetailEvent

}
