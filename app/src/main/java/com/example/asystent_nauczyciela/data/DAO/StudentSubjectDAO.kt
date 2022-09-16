package com.example.asystent_nauczyciela.data.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.entities.Subject
import com.example.asystent_nauczyciela.data.entities.relations.StudentSubjectCrossRef

@Dao
interface StudentSubjectDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudentSubject(studentSubject : StudentSubjectCrossRef)

    @Query("SELECT * FROM subjects INNER JOIN studentssubjects ON studentssubjects.subjectId = subjects.id WHERE studentssubjects.studentId = :id")
    fun readStudentSubjects(id : Int) : LiveData<List<Subject>>

    @Query("SELECT * FROM students INNER JOIN studentssubjects ON studentssubjects.studentId = students.id WHERE studentssubjects.subjectId = :id")
    fun readSubjectStudents(id : Int) : LiveData<List<Student>>

}