package com.example.al_mudarris.database.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Relation

@Entity(
    "assessment_scores",
    primaryKeys = ["studentId", "assessmentId"],
    foreignKeys = [
        ForeignKey(
            entity = Assessment::class,
            parentColumns = ["id"],
            childColumns = ["assessmentId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AssessmentScore(
    val assessmentId: Int,
    val studentId: Int,
    var score: Int
)

data class ScoreWithAssessmentAndStudent(
    @Embedded
    val scores: AssessmentScore,
    @Relation(
        entity = Assessment::class,
        parentColumn = "assessmentId",
        entityColumn = "id",
    )
    val assessment: Assessment,
    @Relation(
        entity = Student::class,
        parentColumn = "studentId",
        entityColumn = "id"
    )
    val student: Student
)