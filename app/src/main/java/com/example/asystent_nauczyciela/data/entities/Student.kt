package com.example.asystent_nauczyciela.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val firstName : String,
    val lastName : String,
    val albumNumber : Int
) : Parcelable