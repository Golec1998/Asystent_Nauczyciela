package com.example.asystent_nauczyciela.data.repositories

import com.example.asystent_nauczyciela.data.DAO.ClearDAO

class ClearRepository(private val clearDAO : ClearDAO) {

    suspend fun nukeTheBase() {
        clearDAO.deleteAllStudents()
        clearDAO.deleteAllSubjects()
        clearDAO.deleteAllStudentsSubjects()
    }

}