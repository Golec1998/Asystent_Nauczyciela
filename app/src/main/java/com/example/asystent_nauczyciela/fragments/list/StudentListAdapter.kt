package com.example.asystent_nauczyciela.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent_nauczyciela.R
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.viewModel.StudentViewModel
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(private val mStudentViewModel : StudentViewModel) : RecyclerView.Adapter<StudentListAdapter.StudentListViewHolder>() {

    private var studentList = emptyList<Student>()

    class StudentListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : StudentListAdapter.StudentListViewHolder {
        return StudentListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.student_list_item, parent, false))
    }

    override fun getItemCount() : Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentListAdapter.StudentListViewHolder, position: Int) {
        val currentItem = studentList[position]
        holder.itemView.studentListItemFirstName.text = currentItem.firstName.toString()
        holder.itemView.studentListItemLastName.text = currentItem.lastName.toString()
        holder.itemView.studentListItemAlbumNumber.text = "(" + currentItem.albumNumber.toString() + ")"

        holder.itemView.studentListItem.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentListFragmentToStudentSubjectListFragment(currentItem)
            mStudentViewModel.selectStudent(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(student : List<Student>) {
        this.studentList = student
        notifyDataSetChanged()
    }

}