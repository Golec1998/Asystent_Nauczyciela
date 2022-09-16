package com.example.asystent_nauczyciela.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystent_nauczyciela.data.MyDatabase
import com.example.asystent_nauczyciela.data.entities.Subject
import com.example.asystent_nauczyciela.data.repositories.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectViewModel(application : Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Subject>>
    private val repository : SubjectRepository

    init {
        val subjectDAO = MyDatabase.getDatabase(application).subjectDAO()
        repository = SubjectRepository(subjectDAO)
        readAllData = repository.readAllData
    }

    fun addSubject(subject : Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(subject)
        }
    }

    fun updateSubject(subject : Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSubject(subject)
        }
    }

    fun deleteSubject(subject : Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSubject(subject)
        }
    }

}