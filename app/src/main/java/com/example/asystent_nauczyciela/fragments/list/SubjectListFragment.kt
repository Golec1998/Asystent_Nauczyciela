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
import com.example.asystent_nauczyciela.data.viewModel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_subject_list.view.*

class SubjectListFragment : Fragment() {

    private lateinit var mSubjectViewModel : SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subject_list, container, false)

        val adapter = SubjectListAdapter()
        val recyclerView = view.subjectRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mSubjectViewModel.readAllData.observe(viewLifecycleOwner, Observer { subject ->
            adapter.setData(subject)
        })

        view.addSubjectToListButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectListFragment_to_subjectAddFragment)
        }

        return view
    }

}