package com.example.asystent_nauczyciela.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent_nauczyciela.R
import com.example.asystent_nauczyciela.data.entities.Subject
import kotlinx.android.synthetic.main.subject_list_item.view.*

class SubjectListAdapter : RecyclerView.Adapter<SubjectListAdapter.SubjectListViewHolder>() {

    private var subjectList = emptyList<Subject>()
    private val classesHours = listOf("8:00", "8:15", "8:30", "8:45",
        "9:00", "9:15", "9:30", "9:45",
        "10:00", "10:15", "10:30", "10:45",
        "11:00", "11:15", "11:30", "11:45",
        "12:00", "12:15", "12:30", "12:45",
        "13:00", "13:15", "13:30", "13:45",
        "14:00", "14:15", "14:30", "14:45",
        "15:00", "15:15", "15:30", "15:45",
        "16:00", "16:15", "16:30", "16:45",
        "17:00", "17:15", "17:30", "17:45",
        "18:00", "18:15", "18:30", "18:45")

    class SubjectListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectListViewHolder {
        return SubjectListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.subject_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectListViewHolder, position: Int) {
        val currentItem = subjectList[position]
        holder.itemView.subjectListItemName.text = currentItem.name.toString()
        holder.itemView.subjectListItemDay.text = currentItem.day.toString()
        holder.itemView.subjectListItemHours.text = classesHours[currentItem.startHour] + " - " + classesHours[currentItem.startHour + (currentItem.duration + 1) * 2 + 2]

        holder.itemView.subjectListItem.setOnClickListener {
            val action = SubjectListFragmentDirections.actionSubjectListFragmentToSubjectUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(subject : List<Subject>) {
        this.subjectList = subject
        notifyDataSetChanged()
    }

}