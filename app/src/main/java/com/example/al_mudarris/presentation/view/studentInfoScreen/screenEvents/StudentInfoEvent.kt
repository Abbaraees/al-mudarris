package com.example.al_mudarris.presentation.view.studentInfoScreen.screenEvents

sealed interface StudentInfoEvent {
    object ShowDeleteDialog: StudentInfoEvent
    object HideDeleteDialog: StudentInfoEvent
    object DeleteStudent: StudentInfoEvent
    object ShowUpdateStudentDialog: StudentInfoEvent
    object HideUpdateStudentDialog: StudentInfoEvent
    object UpdateStudentInfo: StudentInfoEvent
    data class LoadStudentInfo(val studentId: Int): StudentInfoEvent
    data class SetName(val name: String): StudentInfoEvent
    data class SetGender(val gender: String): StudentInfoEvent
    data class SetDob(val dob: String): StudentInfoEvent
    data class SetEmergencyContact(val emergencyContact: String): StudentInfoEvent
    data class SetComment(val comment: String): StudentInfoEvent


}