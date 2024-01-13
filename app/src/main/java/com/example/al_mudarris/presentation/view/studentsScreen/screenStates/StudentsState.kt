package com.example.al_mudarris.presentation.view.studentsScreen.screenStates

import com.example.al_mudarris.database.entites.Student

data class StudentsState(
    val students: List<Student> = listOf(),
    val name: String = "",
    var gender: String = "",
    val dob: String = "",
    val emergencyContact: String = "",
    val searchValue: String = "",
    val isAdding: Boolean = false
)