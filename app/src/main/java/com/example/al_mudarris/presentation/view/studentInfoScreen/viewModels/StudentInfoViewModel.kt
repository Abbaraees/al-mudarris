package com.example.al_mudarris.presentation.view.studentInfoScreen.viewModels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.al_mudarris.database.daos.StudentDao
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenEvents.StudentInfoEvent
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenStates.StudentInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentInfoViewModel(
    private val studentDao: StudentDao
): ViewModel() {
    private val _state = MutableStateFlow(StudentInfoState())

    val state = _state


    fun loadStudent(studentId: Int) {
       viewModelScope.launch {
           val student = studentDao.searchStudentById(studentId)
           if (student != null) {
               _state.update {
                   it.copy(
                       name = student.name,
                       gender = student.gender,
                       dob = student.dob,
                       id = student.id
                   )
               }
           }
       }
    }

    fun onEvent(event: StudentInfoEvent) {
        when (event) {
            StudentInfoEvent.HideDeleteDialog -> {
                _state.update {
                    it.copy(showDeleteDialog = false)
                }
            }
            StudentInfoEvent.ShowDeleteDialog -> {
                _state.update {
                    it.copy(showDeleteDialog = true)
                }
            }

            is StudentInfoEvent.LoadStudentInfo -> {
                loadStudent(event.studentId)
            }

            StudentInfoEvent.DeleteStudent -> {
                viewModelScope.launch {
                    val student = studentDao.searchStudentById(_state.value.id)
                    studentDao.deleteStudent(student)
                }
            }
        }
    }

}