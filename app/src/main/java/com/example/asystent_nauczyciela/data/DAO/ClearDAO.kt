package com.example.asystent_nauczyciela.data.DAO

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClearDAO {

    @Query("DELETE FROM students")
    suspend fun deleteAllStudents()

    @Query("DELETE FROM subjects")
    suspend fun deleteAllSubjects()

    @Query("DELETE FROM studentssubjects")
    suspend fun deleteAllStudentsSubjects()

}