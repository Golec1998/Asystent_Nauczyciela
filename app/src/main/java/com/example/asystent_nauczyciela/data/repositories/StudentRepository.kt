package com.example.asystent_nauczyciela.data.repositories

import androidx.lifecycle.LiveData
import com.example.asystent_nauczyciela.data.DAO.StudentDAO
import com.example.asystent_nauczyciela.data.entities.Student

class StudentRepository(private val studentDAO : StudentDAO) {

    val readAllData : LiveData<List<Student>> = studentDAO.readAllData()

    suspend fun addStudent(student : Student) {
        studentDAO.addStudent(student)
    }

    suspend fun updateStudent(student : Student) {
        studentDAO.updateStudent(student)
    }

    suspend fun deleteStudent(student : Student) {
        studentDAO.deleteStudent(student)
    }

}