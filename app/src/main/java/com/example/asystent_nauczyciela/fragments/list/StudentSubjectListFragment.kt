package com.example.asystent_nauczyciela.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent_nauczyciela.R
import com.example.asystent_nauczyciela.data.viewModel.StudentSubjectViewModel
import com.example.asystent_nauczyciela.data.viewModel.StudentViewModel
import com.example.asystent_nauczyciela.fragments.update.SubjectUpdateFragmentArgs
import kotlinx.android.synthetic.main.fragment_student_list.view.*
import kotlinx.android.synthetic.main.fragment_student_subject_list.view.*

class StudentSubjectListFragment : Fragment() {

    private val args by navArgs<StudentSubjectListFragmentArgs>()
    //lateinit var mStudentSubjectViewModel : StudentSubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_subject_list, container, false)

        val adapter = SubjectListAdapter()
        val recyclerView = view.subjectRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        mStudentSubjectViewModel = ViewModelProvider(this).get(StudentSubjectViewModel::class.java)
//        mStudentSubjectViewModel.subjects.observe(viewLifecycleOwner, Observer { subject ->
//            adapter.setData(subject)
//        })

        view.editStudentButton.setOnClickListener {
            val action = StudentSubjectListFragmentDirections.actionStudentSubjectListFragmentToStudentUpdateFragment(args.currentStudent)
            findNavController().navigate(action)
        }

        return view
    }

}