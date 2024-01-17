package com.example.al_mudarris.presentation.view.studentInfoScreen.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.al_mudarris.database.daos.StudentDao
import com.example.al_mudarris.database.entites.Student
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenEvents.StudentInfoEvent
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenStates.StudentInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentInfoViewModel(
    private val studentDao: StudentDao
): ViewModel() {
    private val _state = MutableStateFlow(StudentInfoState())

    val state = _state

    private fun loadStudent(studentId: Int) {
       viewModelScope.launch {
           val student = studentDao.searchStudentById(studentId)
           if (student != null) {
               _state.update {
                   it.copy(
                       name = student.name,
                       gender = student.gender,
                       dob = student.dob,
                       id = student.id,
                       emergencyContact = student.emergencyContact
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

            StudentInfoEvent.HideUpdateStudentDialog -> {
                _state.update {
                    it.copy(
                        showUpdateDialog = false
                    )
                }
            }
            StudentInfoEvent.ShowUpdateStudentDialog -> {
                _state.update {
                    it.copy(
                        showUpdateDialog = true
                    )
                }
            }

            is StudentInfoEvent.SetComment -> {
                _state.update {
                    it.copy(comment = event.comment)
                }
            }
            is StudentInfoEvent.SetDob -> {
                _state.update {
                    it.copy(dob = event.dob)
                }
            }
            is StudentInfoEvent.SetEmergencyContact -> {
                _state.update {
                    it.copy(emergencyContact = event.emergencyContact)
                }
            }
            is StudentInfoEvent.SetGender -> {
                _state.update {
                    it.copy(gender = event.gender)
                }
            }
            is StudentInfoEvent.SetName -> {
                _state.update {
                    it.copy(name = event.name)
                }
            }

            StudentInfoEvent.UpdateStudentInfo -> {
                val student = Student(
                    id = _state.value.id,
                    name = _state.value.name,
                    gender = _state.value.gender,
                    emergencyContact = _state.value.emergencyContact,
                    dob = _state.value.dob
                )
                viewModelScope.launch {
                    studentDao.addStudent(student)
                    loadStudent(_state.value.id)
                }
            }
        }
    }

}