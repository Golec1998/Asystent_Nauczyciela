package com.example.asystent_nauczyciela.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asystent_nauczyciela.data.MyDatabase
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.repositories.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application : Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Student>>
    private val repository : StudentRepository
    private val _selected = MutableLiveData<Student>()

    init {
        val studentDAO = MyDatabase.getDatabase(application).studentDAO()
        repository = StudentRepository(studentDAO)
        readAllData = repository.readAllData
    }

    fun addStudent(student : Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }

    fun updateStudent(student : Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStudent(student)
        }
    }

    fun deleteStudent(student : Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStudent(student)
        }
    }

    fun selectStudent(student : Student) {
        _selected.value = student
    }

    fun getStudent() = _selected.value

}