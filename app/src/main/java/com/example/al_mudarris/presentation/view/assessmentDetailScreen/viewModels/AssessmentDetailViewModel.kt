package com.example.al_mudarris.presentation.view.assessmentDetailScreen.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.al_mudarris.database.daos.AssessmentDao
import com.example.al_mudarris.database.entites.Assessment
import com.example.al_mudarris.presentation.view.assessmentDetailScreen.screenEvents.AssessmentDetailEvent
import com.example.al_mudarris.presentation.view.assessmentDetailScreen.screenStates.AssessmentDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssessmentDetailViewModel(
    private val assessmentDao: AssessmentDao
): ViewModel() {
    private val _state = MutableStateFlow(AssessmentDetailState())
    val state = _state


    private fun loadAssessmentDetail(assessmentId: Int) {
        viewModelScope.launch {
            val assessment = assessmentDao.getAssessment(assessmentId)
            val studentScores =  assessmentDao.getScoresForAssessment(assessmentId)
            val averageScore = studentScores.sumOf { it.scores.score } / studentScores.size.toDouble()

            if (assessment != null) {
                _state.update {
                    it.copy(
                        assessment = assessment,
                        title = assessment.title,
                        description = assessment.description,
                        dueDate = assessment.dueDate,
                        releaseDate = assessment.releaseDate,
                        studentScores = studentScores,
                        averageScore = averageScore
                    )
                }
            }
        }
    }

    fun onEvent(event: AssessmentDetailEvent) {
        when (event) {
            AssessmentDetailEvent.HideUpdateAssessmentDialog -> {
                _state.update { it.copy(
                    isEditing = false
                ) }
            }
            is AssessmentDetailEvent.SetAverageScore -> {
                _state.update { it.copy(
                    averageScore = event.averageScore
                ) }
            }
            is AssessmentDetailEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                ) }
            }
            is AssessmentDetailEvent.SetDueDate -> {
                _state.update { it.copy(
                    dueDate = event.dueDate
                ) }
            }
            is AssessmentDetailEvent.SetReleaseDate -> {
                _state.update { it.copy(
                    releaseDate = event.releaseDate
                ) }
            }
            is AssessmentDetailEvent.SetTitle -> {
                _state.update { it.copy(
                    title = event.title
                ) }
            }
            AssessmentDetailEvent.ShowUpdateAssessmentDialog -> {
                _state.update { it.copy(
                    isEditing = true
                ) }
            }
            AssessmentDetailEvent.UpdateAssessment -> {
                val assessment = Assessment(
                    id = _state.value.assessment?.id ?: 0,
                    title = _state.value.title,
                    description = _state.value.description,
                    releaseDate = _state.value.releaseDate,
                    dueDate = _state.value.dueDate
                )
                viewModelScope.launch {
                    assessmentDao.addAssessment(assessment)
                }
            }
            is AssessmentDetailEvent.LoadAssessmentDetail -> {
                loadAssessmentDetail(event.assessmentId)
            }

            AssessmentDetailEvent.HideDeleteAssessmentDialog -> {
                _state.update { it.copy(
                    showDeleteDialog = false
                ) }
            }
            AssessmentDetailEvent.ShowDeleteAssessmentDialog -> {
                _state.update { it.copy(
                    showDeleteDialog = true
                ) }
            }

            AssessmentDetailEvent.DeleteAssessment -> {
                viewModelScope.launch {
                    _state.value.assessment?.let { assessmentDao.deleteAssessment(it) }
                }
            }
        }
    }
}