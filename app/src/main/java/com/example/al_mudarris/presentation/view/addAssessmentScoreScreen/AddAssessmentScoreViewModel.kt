package com.example.al_mudarris.presentation.view.addAssessmentScoreScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.al_mudarris.database.daos.AssessmentDao
import com.example.al_mudarris.database.daos.AssessmentScoreDao
import com.example.al_mudarris.database.daos.StudentDao
import com.example.al_mudarris.database.entites.AssessmentScore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class AddAssessmentScoreViewModel(
    private val assessmentScoreDao: AssessmentScoreDao,
    private val assessmentDao: AssessmentDao,
    private val studentDao: StudentDao,
    private val assessmentId: Int,
): ViewModel() {
    private val _state = MutableStateFlow(AddAssessmentScoreState())
    private val studentScores = MutableStateFlow(true).flatMapLatest {
        assessmentScoreDao.getAssessmentScores()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    private val students = MutableStateFlow(true).flatMapLatest {
        studentDao.getStudentsOrderedByName()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    val state = combine(_state, studentScores, students) {state, studentScores, students->
        val assessment = assessmentDao.getAssessment(assessmentId)
        state.copy(
            studentScores = studentScores.toMutableList(),
            students = students,
            assessment = assessment
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AddAssessmentScoreState())

    fun getScore(studentId: Int): String {
        val studentScore = studentScores.value.firstOrNull {
            it.studentId == studentId && it.assessmentId == assessmentId
        }
            ?: return ""

        return studentScore.score.toString()
    }

    fun setScore(studentId: Int, score: String) {
        var studentScore = _state.value.studentScores.firstOrNull {
            it.studentId == studentId && it.assessmentId == assessmentId
        }
        if (studentScore == null) {
            studentScore = AssessmentScore(assessmentId, studentId, if (score.isBlank()) 0 else score.toInt())
            _state.value.studentScores.add(studentScore)
        }

        studentScore.score = if (score.isBlank()) 0 else score.toInt()
    }

    fun onEvent(event: AddAssessmentScoreEvent) {
        when (event) {
            AddAssessmentScoreEvent.SaveScores -> {
                viewModelScope.launch {
                    assessmentScoreDao.addAssessmentScores(_state.value.studentScores)
                }
            }
        }
    }
}