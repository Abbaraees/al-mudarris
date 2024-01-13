package com.example.al_mudarris.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("students")
data class Student(
    @PrimaryKey(true)
    val id: Int = 0,
    val name: String,
    val dob: String,
    val gender: String,
    val emergencyContact: String
)
