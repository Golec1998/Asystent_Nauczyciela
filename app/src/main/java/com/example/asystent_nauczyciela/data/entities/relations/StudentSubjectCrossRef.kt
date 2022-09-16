package com.example.asystent_nauczyciela.data.entities.relations

import androidx.room.Entity

@Entity(tableName = "studentssubjects", primaryKeys = ["studentId", "subjectId"])
data class StudentSubjectCrossRef(
    val studentId : Int,
    val subjectId : Int
)