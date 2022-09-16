package com.example.asystent_nauczyciela.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.asystent_nauczyciela.R
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.viewModel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_student_add.*
import kotlinx.android.synthetic.main.fragment_student_add.view.*
import kotlinx.android.synthetic.main.fragment_student_update.*
import kotlinx.android.synthetic.main.fragment_student_update.view.*

class StudentUpdateFragment : Fragment() {

    private val args by navArgs<StudentUpdateFragmentArgs>()
    private lateinit var mStudentViewModel : StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_update, container, false)

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        view.updateStudentFirstName.setText(args.currentStudent.firstName)
        view.updateStudentLastName.setText(args.currentStudent.lastName)
        view.updateStudentAlbumNumber.setText(args.currentStudent.albumNumber.toString())

        view.updateStudentButton.setOnClickListener {
            updateDataInDatabase()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateDataInDatabase() {
        val firstName = updateStudentFirstName.text.toString()
        val lastName = updateStudentLastName.text.toString()
        val albumNumber = updateStudentAlbumNumber.text

        val inputCheck = inputCheck(firstName, lastName, albumNumber)
        if(inputCheck == 0) {
            val student = Student(args.currentStudent.id, firstName, lastName, Integer.parseInt(albumNumber.toString()))
            mStudentViewModel.updateStudent(student)
            Toast.makeText(requireContext(), "Zmodyfikowano studenta", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_studentUpdateFragment_to_studentSubjectListFragment)
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

    override fun onCreateOptionsMenu(menu : Menu, inflater : MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        if (item.itemId == R.id.deleteMenu) {
            deleteStudent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteStudent() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Tak") { _, _ ->
            mStudentViewModel.deleteStudent(args.currentStudent)
            findNavController().navigate(R.id.action_studentUpdateFragment_to_studentListFragment)
            Toast.makeText(requireContext(), "Usunięto studenta ${args.currentStudent.firstName} ${args.currentStudent.lastName}", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Nie") { _, _ -> }
        builder.setTitle("Usuń studenta")
        builder.setMessage("Czy chcesz usunąć studenta ${args.currentStudent.firstName} ${args.currentStudent.lastName}?")
        builder.create().show()
    }

}
