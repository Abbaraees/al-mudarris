package com.example.al_mudarris.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.al_mudarris.database.entites.AssessmentScore
import com.example.al_mudarris.database.entites.ScoreWithAssessmentAndStudent
import kotlinx.coroutines.flow.Flow

@Dao
interface AssessmentScoreDao {
    @Upsert
    suspend fun addAssessmentScores(scores: List<AssessmentScore>)

    @Delete
    suspend fun deleteAssessmentScore(score: AssessmentScore)

    @Query("SELECT * FROM assessment_scores")
    fun getAssessmentScores(): Flow<List<AssessmentScore>>
}