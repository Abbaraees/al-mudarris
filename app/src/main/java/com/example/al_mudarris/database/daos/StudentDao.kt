package com.example.al_mudarris.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.al_mudarris.database.entites.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Upsert
    suspend fun addStudent(student: Student)

    @Query("SELECT * FROM students ORDER BY name")
    fun getStudentsOrderedByName(): Flow<List<Student>>

    @Query("SELECT * FROM students WHERE name LIKE '%' || :name ||  '%'")
    fun searchStudent(name: String): Flow<List<Student>>

    @Delete
    fun deleteStudent(student: Student)
}