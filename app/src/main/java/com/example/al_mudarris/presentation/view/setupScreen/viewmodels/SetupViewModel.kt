package com.example.al_mudarris.presentation.view.setupScreen.viewmodels

import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.example.al_mudarris.presentation.view.setupScreen.screenEvents.SetupEvent
import com.example.al_mudarris.presentation.view.setupScreen.screenStates.SetupState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SetupViewModel: ViewModel() {
    private val _state = MutableStateFlow(SetupState())

    val state = _state


    fun onEvent(event: SetupEvent) {
        when (event) {
            is SetupEvent.SetClassName -> {
                _state.update { it.copy(
                    className = event.className
                )
                }
            }
            is SetupEvent.SetSchoolName -> {
                _state.update { it.copy(
                    schoolName = event.schoolName
                )
                }
            }
            is SetupEvent.SetTeacherName -> {
                _state.update { it.copy(
                    teacherName = event.teacherName
                )
                }
            }

            is SetupEvent.SetupApp -> {
                val schoolName = state.value.schoolName
                val className = state.value.className
                val teacherName = state.value.teacherName
                val password = state.value.password
                val sharedPreferences = event.sharedPreferences

                if (
                    schoolName.isBlank() || className.isBlank() ||
                    teacherName.isBlank() || password.isBlank()
                    ) {
                    return
                }

                sharedPreferences.edit(true) {
                    putString("schoolName", schoolName)
                    putString("className", className)
                    putString("teacherName", teacherName)
                    putString("password", password)
                }

                state.update { it.copy(
                    isSettingUp = false
                ) }
            }

            is SetupEvent.SetPassword -> {
                _state.update { it.copy(
                    password = event.password
                )
                }
            }

        }
    }
}