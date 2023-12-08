package com.example.al_mudarris.presentation.view.setupScreen.screenEvents

import android.content.SharedPreferences

sealed interface SetupEvent {
    data class SetTeacherName(val teacherName: String): SetupEvent
    data class SetSchoolName(val schoolName: String): SetupEvent
    data class SetClassName(val className: String): SetupEvent
    data class SetPassword(val password: String): SetupEvent
    data class SetupApp(val sharedPreferences: SharedPreferences): SetupEvent
}