package com.example.al_mudarris.database.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(
    "assessments"
)
data class Assessment(
    @PrimaryKey(true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val releaseDate: String,
    val dueDate: String
)
