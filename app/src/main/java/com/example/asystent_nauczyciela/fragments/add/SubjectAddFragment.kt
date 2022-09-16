package com.example.asystent_nauczyciela.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asystent_nauczyciela.R
import com.example.asystent_nauczyciela.data.entities.Subject
import com.example.asystent_nauczyciela.data.viewModel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_subject_add.*
import kotlinx.android.synthetic.main.fragment_subject_add.view.*

class SubjectAddFragment : Fragment() {

    private lateinit var mSubjectViewModel : SubjectViewModel

    private var data = mutableListOf<Int>(0, 0, 0, 0)
    private val days = listOf("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subject_add, container, false)

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)

        view.addSubjectButton.setOnClickListener {
            insertDataToDatabase()
        }

        view.subjectDaySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setData(0, position)
            }

        }
        view.subjectHourSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setData(1, position)
            }

        }
        view.subjectMinuteSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setData(2, position)
            }

        }
        view.subjectDurationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setData(3, position)
            }

        }

        return view
    }

    private fun setData(position: Int, value: Int) {
        data[position] = value
    }

    private fun insertDataToDatabase() {
        val name = addSubjectName.text.toString()
        val day = days[data[0]]
        val startHour = data[1] * 4 + data[2]
        val duration = data[3]

        if (inputCheck(name)) {
            val subject = Subject(0, name, day, startHour, duration)
            mSubjectViewModel.addSubject(subject)
            Toast.makeText(requireContext(), "Dodano przedmiot", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_subjectAddFragment_to_subjectListFragment)
        }
        else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String) : Boolean {
        return name != ""
    }

}