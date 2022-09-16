package com.example.asystent_nauczyciela.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent_nauczyciela.R
import com.example.asystent_nauczyciela.data.viewModel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_student_list.view.*

class StudentListFragment : Fragment() {

    private lateinit var mStudentViewModel : StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        val adapter = StudentListAdapter(mStudentViewModel)
        val recyclerView = view.studentRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mStudentViewModel.readAllData.observe(viewLifecycleOwner, Observer { student ->
            adapter.setData(student)
        })

        view.addStudentToListButton.setOnClickListener {
            findNavController().navigate(R.id.action_studentListFragment_to_studentAddFragment)
        }

        return view
    }

}