package com.example.al_mudarris.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.al_mudarris.database.daos.StudentDao
import com.example.al_mudarris.database.entites.Student

@Database(
    entities = [Student::class],
    version = 1,
    exportSchema = false
)
abstract  class AlmudarrisDatabase: RoomDatabase(){
    abstract val studentDao: StudentDao

}