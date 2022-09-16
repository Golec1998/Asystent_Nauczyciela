package com.example.asystent_nauczyciela.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val day : String,
    val startHour : Int,
    val duration : Int
) : Parcelable