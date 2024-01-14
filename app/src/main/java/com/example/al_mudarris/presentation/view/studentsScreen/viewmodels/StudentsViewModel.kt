package com.example.al_mudarris.presentation.view.studentsScreen.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.al_mudarris.database.daos.StudentDao
import com.example.al_mudarris.database.entites.Student
import com.example.al_mudarris.presentation.view.studentsScreen.screenEvents.StudentsEvent
import com.example.al_mudarris.presentation.view.studentsScreen.screenStates.StudentsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class StudentsViewModel(private val studentDao: StudentDao): ViewModel() {
    private val _state = MutableStateFlow(StudentsState())
    private val searchValue = MutableStateFlow("")

    var students = searchValue.flatMapLatest {
        when {
            it.isBlank() -> studentDao.getStudentsOrderedByName()
            else -> studentDao.searchStudent(it)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, students) {state, students ->
        state.copy(students = students)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StudentsState())


    fun onEvent(event: StudentsEvent) {
        when (event) {
            StudentsEvent.ShowDialog -> {
                _state.update {
                    it.copy(isAdding = true)
                }
            }
            StudentsEvent.HideDialog -> {
                _state.update {
                    it.copy(isAdding = false)
                }
            }
            is StudentsEvent.SetDob -> {
                _state.update {
                    it.copy(dob = event.dob)
                }
            }
            is StudentsEvent.SetEmergencyContact -> {
                _state.update {
                    it.copy(emergencyContact = event.emergencyContact)
                }
            }
            is StudentsEvent.SetGender -> {
                _state.update {
                    it.copy(gender = event.gender)
                }
            }
            is StudentsEvent.SetName -> {
                _state.update {
                    it.copy(name = event.name)
                }
            }
            is StudentsEvent.SetSearchValue -> {
                _state.update {
                    it.copy(searchValue = event.searchValue)
                }
                searchValue.value = event.searchValue
            }

            StudentsEvent.SaveStudent -> {
                if (_state.value.name.isBlank() || _state.value.gender.isBlank() || _state.value.dob.isBlank() ) {
                    return
                }
                else {
                    val student = Student(
                        name = _state.value.name,
                        gender = _state.value.gender,
                        dob = _state.value.dob,
                        emergencyContact = _state.value.emergencyContact
                    )
                    viewModelScope.launch {
                        studentDao.addStudent(student)
                    }
                }

                _state.update {
                    it.copy(isAdding = false)
                }

            }
        }
    }
}