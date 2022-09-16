package com.example.asystent_nauczyciela.data.repositories

import androidx.lifecycle.LiveData
import com.example.asystent_nauczyciela.data.DAO.StudentSubjectDAO
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.entities.Subject
import com.example.asystent_nauczyciela.data.entities.relations.StudentSubjectCrossRef

class StudentSubjectRepository(private val studentSubjectDAO : StudentSubjectDAO) {

    fun readSubjectStudents(id : Int) : LiveData<List<Student>> = studentSubjectDAO.readSubjectStudents(id)

    fun readStudentSubjects(id : Int) : LiveData<List<Subject>> = studentSubjectDAO.readStudentSubjects(id)

    suspend fun addStudent(studentSubject : StudentSubjectCrossRef) {
        studentSubjectDAO.addStudentSubject(studentSubject)
    }

}