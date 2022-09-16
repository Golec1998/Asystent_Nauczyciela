package com.example.asystent_nauczyciela.data.repositories

import androidx.lifecycle.LiveData
import com.example.asystent_nauczyciela.data.DAO.SubjectDAO
import com.example.asystent_nauczyciela.data.entities.Subject

class SubjectRepository(private val subjectDAO : SubjectDAO) {

    val readAllData : LiveData<List<Subject>> = subjectDAO.readAllData()

    suspend fun addSubject(subject : Subject) {
        subjectDAO.addSubject(subject)
    }

    suspend fun updateSubject(subject : Subject) {
        subjectDAO.updateSubject(subject)
    }

    suspend fun deleteSubject(subject : Subject) {
        subjectDAO.deleteSubject(subject)
    }

}