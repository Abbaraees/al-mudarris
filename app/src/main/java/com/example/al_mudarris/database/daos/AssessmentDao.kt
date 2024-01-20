package com.example.al_mudarris.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.al_mudarris.database.entites.Assessment
import com.example.al_mudarris.database.entites.ScoreWithAssessmentAndStudent
import kotlinx.coroutines.flow.Flow

@Dao
interface AssessmentDao {
    @Upsert
    suspend fun addAssessment(assessment: Assessment)

    @Delete
    suspend fun deleteAssessment(assessment: Assessment)

    @Query("SELECT * FROM assessments")
    fun getAssessments(): Flow<List<Assessment>>

    @Transaction
    @Query("SELECT * FROM assessment_scores WHERE assessmentId = :assessmentId")
    fun getScoresForAssessment(assessmentId: Int): Flow<List<ScoreWithAssessmentAndStudent>>
}