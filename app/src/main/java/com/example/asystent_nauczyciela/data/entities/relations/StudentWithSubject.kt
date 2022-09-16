package com.example.asystent_nauczyciela.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.entities.Subject

data class StudentWithSubject (
        @Embedded val student : Student,
        @Relation(
            parentColumn = "studentId",
            entityColumn = "subjectId",
            associateBy = Junction(StudentSubjectCrossRef::class)
        )
        val subject : List<Subject>
)