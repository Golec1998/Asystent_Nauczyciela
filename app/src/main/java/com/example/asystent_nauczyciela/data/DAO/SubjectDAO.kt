package com.example.asystent_nauczyciela.data.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystent_nauczyciela.data.entities.Subject

@Dao
interface SubjectDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(subject : Subject)

    @Update
    suspend fun updateSubject(subject : Subject)

    @Delete
    suspend fun deleteSubject(subject : Subject)

    @Query("SELECT * FROM subjects ORDER BY name ASC")
    fun readAllData() : LiveData<List<Subject>>

}