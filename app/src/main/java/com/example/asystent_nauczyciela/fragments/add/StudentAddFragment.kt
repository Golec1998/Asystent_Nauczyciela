package com.example.asystent_nauczyciela.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asystent_nauczyciela.R
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.viewModel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_student_add.*
import kotlinx.android.synthetic.main.fragment_student_add.view.*

class StudentAddFragment : Fragment() {

    private lateinit var mStudentViewModel : StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_add, container, false)

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        view.addStudentButton.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addStudentFirstName.text.toString()
        val lastName = addStudentLastName.text.toString()
        val albumNumber = addStudentAlbumNumber.text

        val inputCheck = inputCheck(firstName, lastName, albumNumber)
        if(inputCheck == 0) {
            val student = Student(0, firstName, lastName, Integer.parseInt(albumNumber.toString()))
            mStudentViewModel.addStudent(student)
            Toast.makeText(requireContext(), "Dodano studenta", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_studentAddFragment_to_studentListFragment)
        }
        else if (inputCheck == 1) {
            Toast.makeText(requireContext(), "Podaj właściwy numer albumu", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName : String, lastName : String, albumNumber : Editable) : Int {
        if(firstName == "" || lastName == "" || albumNumber.toString() == "")
            return 2
        else if(albumNumber.toString().toInt() < 100000 || albumNumber.toString().toInt() > 999999)
            return 1
        else
            return 0
    }

}