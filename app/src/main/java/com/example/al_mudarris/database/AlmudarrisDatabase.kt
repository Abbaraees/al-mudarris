package com.example.al_mudarris.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.al_mudarris.database.daos.AssessmentDao
import com.example.al_mudarris.database.daos.AssessmentScoreDao
import com.example.al_mudarris.database.daos.StudentDao
import com.example.al_mudarris.database.entites.Assessment
import com.example.al_mudarris.database.entites.AssessmentScore
import com.example.al_mudarris.database.entites.Student

@Database(
    entities = [Student::class, Assessment::class, AssessmentScore::class],
    version = 1,
    exportSchema = false
)
abstract  class AlmudarrisDatabase: RoomDatabase(){
    abstract val studentDao: StudentDao
    abstract val assessmentDao: AssessmentDao
    abstract val assessmentScoreDao: AssessmentScoreDao

}