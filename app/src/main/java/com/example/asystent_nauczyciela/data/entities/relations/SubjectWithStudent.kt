package com.example.asystent_nauczyciela.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.entities.Subject

data class SubjectWithStudent(
    @Embedded val subject : Subject,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "studentId",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val student : List<Student>
)