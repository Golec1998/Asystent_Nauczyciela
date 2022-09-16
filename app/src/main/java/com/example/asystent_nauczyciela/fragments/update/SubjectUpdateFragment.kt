package com.example.asystent_nauczyciela.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.asystent_nauczyciela.R
import com.example.asystent_nauczyciela.data.entities.Subject
import com.example.asystent_nauczyciela.data.viewModel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_student_update.view.*
import kotlinx.android.synthetic.main.fragment_subject_add.*
import kotlinx.android.synthetic.main.fragment_subject_add.addSubjectName
import kotlinx.android.synthetic.main.fragment_subject_add.view.*
import kotlinx.android.synthetic.main.fragment_subject_add.view.addSubjectButton
import kotlinx.android.synthetic.main.fragment_subject_update.*
import kotlinx.android.synthetic.main.fragment_subject_update.view.*

class SubjectUpdateFragment : Fragment() {

    private val args by navArgs<SubjectUpdateFragmentArgs>()
    private lateinit var mSubjectViewModel : SubjectViewModel

    private val days = listOf("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota")
    private var data = mutableListOf<Int>(0, 0, 0, 0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subject_update, container, false)

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)

        setData(0, days.indexOf(args.currentSubject.day))
        setData(1, args.currentSubject.startHour / 4)
        setData(2, args.currentSubject.startHour % 4)
        setData(3, args.currentSubject.duration)

        view.updateSubjectName.setText(args.currentSubject.name)
        view.updateSubjectDaySpinner.setSelection(data[0])
        view.updateSubjectHourSpinner.setSelection(data[1])
        view.updateSubjectMinuteSpinner.setSelection(data[2])
        view.updateSubjectDurationSpinner.setSelection(data[3])

        view.updateSubjectButton.setOnClickListener {
            updateDataInDatabase()
        }

        view.updateSubjectDaySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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
        view.updateSubjectHourSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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
        view.updateSubjectMinuteSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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
        view.updateSubjectDurationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu : Menu, inflater : MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        if (item.itemId == R.id.deleteMenu) {
            deleteSubject()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteSubject() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Tak") { _, _ ->
            mSubjectViewModel.deleteSubject(args.currentSubject)
            findNavController().navigate(R.id.action_subjectUpdateFragment_to_subjectListFragment)
            Toast.makeText(requireContext(), "Usunięto przedmiot ${args.currentSubject.name}", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Nie") { _, _ -> }
        builder.setTitle("Usuń przedmiot")
        builder.setMessage("Czy chcesz usunąć przedmiot ${args.currentSubject.name}?")
        builder.create().show()
    }

    private fun setData(position: Int, value: Int) {
        data[position] = value
    }

    private fun updateDataInDatabase() {
        val name = updateSubjectName.text.toString()
        val day = days[data[0]]
        val startHour = data[1] * 4 + data[2]
        val duration = data[3]

        if (inputCheck(name)) {
            val subject = Subject(args.currentSubject.id, name, day, startHour, duration)
            mSubjectViewModel.updateSubject(subject)
            Toast.makeText(requireContext(), "Edytowano przedmiot", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_subjectUpdateFragment_to_subjectListFragment)
        }
        else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String) : Boolean {
        return name != ""
    }

}