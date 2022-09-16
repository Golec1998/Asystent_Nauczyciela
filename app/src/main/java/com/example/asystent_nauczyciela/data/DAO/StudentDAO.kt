package com.example.asystent_nauczyciela.data.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystent_nauczyciela.data.entities.Student

@Dao
interface StudentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student : Student)

    @Update
    suspend fun updateStudent(student : Student)

    @Delete
    suspend fun deleteStudent(student : Student)

    @Query("SELECT * FROM students ORDER BY lastName ASC")
    fun readAllData() : LiveData<List<Student>>

}