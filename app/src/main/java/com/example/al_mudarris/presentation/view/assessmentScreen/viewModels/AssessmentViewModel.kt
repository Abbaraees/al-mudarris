package com.example.al_mudarris.presentation.view.assessmentScreen.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.al_mudarris.database.daos.AssessmentDao
import com.example.al_mudarris.database.entites.Assessment
import com.example.al_mudarris.presentation.view.assessmentScreen.screenEvents.AssessmentEvent
import com.example.al_mudarris.presentation.view.assessmentScreen.screenStates.AssessmentState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class AssessmentViewModel(
    private val assessmentDao: AssessmentDao
): ViewModel() {
    private val _state = MutableStateFlow(AssessmentState())
    private val loadAssessments = MutableStateFlow(true)
    private val assessments = loadAssessments.flatMapLatest {
        assessmentDao.getAssessments()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, assessments) { state, assessments ->
        state.copy(assessments = assessments)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AssessmentState())

    fun onEvent(event: AssessmentEvent) {
        when (event) {
            AssessmentEvent.AddAssessment -> {
                if (
                    _state.value.title.isBlank() ||
                    _state.value.releaseDate.isBlank() ||
                    _state.value.dueDate.isBlank()
                    ) {
                    return
                }

                val assessment = Assessment(
                    title = _state.value.title,
                    description = _state.value.description,
                    releaseDate = _state.value.releaseDate,
                    dueDate = _state.value.dueDate
                )

                viewModelScope.launch {
                    assessmentDao.addAssessment(assessment)
                }
            }
            AssessmentEvent.HideAddAssessmentDialog -> {
                _state.update { it.copy(
                    isAdding = false
                ) }
            }
            is AssessmentEvent.SetDueDate -> {
                _state.update { it.copy(
                    dueDate = event.dueDate
                ) }
            }
            is AssessmentEvent.SetReleaseDate -> {
                _state.update { it.copy(
                    releaseDate = event.releaseDate
                ) }
            }
            is AssessmentEvent.SetTitle -> {
                _state.update { it.copy(
                    title = event.title
                ) }
            }
            AssessmentEvent.ShowAddAssessmentDialog -> {
                _state.update { it.copy(
                    isAdding = true
                ) }
            }

            is AssessmentEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                ) }
            }

            AssessmentEvent.ResetInputs -> {
                _state.update { it.copy(
                    title = "",
                    releaseDate = "",
                    dueDate = "",
                    description = "",
                    isAdding = false
                ) }
            }
        }
    }
}