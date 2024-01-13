package com.example.al_mudarris.presentation.view.studentsScreen.screenEvents

sealed interface StudentsEvent {
    data class SetName(val name: String): StudentsEvent
    data class SetDob(val dob: String): StudentsEvent
    data class SetGender(val gender: String): StudentsEvent
    data class SetEmergencyContact(val emergencyContact: String): StudentsEvent
    data class SetSearchValue(val searchValue: String): StudentsEvent
    object ShowDialog: StudentsEvent
    object HideDialog: StudentsEvent
    object SaveStudent: StudentsEvent

}