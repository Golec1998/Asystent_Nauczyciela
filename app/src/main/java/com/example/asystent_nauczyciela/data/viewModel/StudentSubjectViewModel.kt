package com.example.asystent_nauczyciela.data.viewModel

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.asystent_nauczyciela.data.MyDatabase
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.entities.Subject
import com.example.asystent_nauczyciela.data.repositories.StudentSubjectRepository

class StudentSubjectViewModel(application : Application) : AndroidViewModel(application) {

    val subjects : LiveData<List<Subject>>
    //val students : LiveData<List<Student>>
    private val repository : StudentSubjectRepository

    lateinit var studentViewModel : StudentViewModel
    private val student = studentViewModel.getStudent()

    init {
        val studentSubjectDAO = MyDatabase.getDatabase(application).studentSubjectDAO()
        repository = StudentSubjectRepository(studentSubjectDAO)

        //studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        subjects = repository.readStudentSubjects(student!!.id)
        //students = repository.readSubjectStudents(subject!!.id)
    }

}