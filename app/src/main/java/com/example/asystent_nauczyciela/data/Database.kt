package com.example.asystent_nauczyciela.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asystent_nauczyciela.data.DAO.ClearDAO
import com.example.asystent_nauczyciela.data.DAO.StudentDAO
import com.example.asystent_nauczyciela.data.DAO.StudentSubjectDAO
import com.example.asystent_nauczyciela.data.DAO.SubjectDAO
import com.example.asystent_nauczyciela.data.entities.Student
import com.example.asystent_nauczyciela.data.entities.Subject
import com.example.asystent_nauczyciela.data.entities.relations.StudentSubjectCrossRef

@Database(entities = [Student::class, Subject::class, StudentSubjectCrossRef::class], version = 3, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun studentDAO() : StudentDAO
    abstract fun subjectDAO() : SubjectDAO
    abstract fun studentSubjectDAO() : StudentSubjectDAO
    abstract fun clearDAO() : ClearDAO

    companion object {
        @Volatile
        private var INSTANCE : MyDatabase? = null

        fun getDatabase(context : Context) : MyDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}