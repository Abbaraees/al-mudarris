package com.example.al_mudarris.presentation.view.studentInfoScreen.screenEvents

sealed interface StudentInfoEvent {
    object ShowDeleteDialog: StudentInfoEvent
    object HideDeleteDialog: StudentInfoEvent
    object DeleteStudent: StudentInfoEvent
    data class LoadStudentInfo(val studentId: Int): StudentInfoEvent

}