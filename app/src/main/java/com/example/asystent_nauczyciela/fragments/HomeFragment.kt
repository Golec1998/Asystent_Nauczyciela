package com.example.asystent_nauczyciela.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asystent_nauczyciela.R
import com.example.asystent_nauczyciela.data.viewModel.ClearViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var mClearViewModel : ClearViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        mClearViewModel = ViewModelProvider(this).get(ClearViewModel::class.java)

        view.studentListButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_studentListFragment)
        }

        view.subjectsListButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_subjectListFragment)
        }



        view.clearDatabaseButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Tak") { _, _ ->
                mClearViewModel.nukeTheBase()
                Toast.makeText(requireContext(), "Baza wyczyszczona", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Nie") { _, _ -> }
            builder.setTitle("Usuń dane")
            builder.setMessage("Czy chcesz wyczyścić wszystkie dane z bazy?")
            builder.create().show()
        }

        return view
    }

}