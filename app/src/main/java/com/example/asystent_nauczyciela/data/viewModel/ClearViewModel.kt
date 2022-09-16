package com.example.asystent_nauczyciela.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystent_nauczyciela.data.MyDatabase
import com.example.asystent_nauczyciela.data.repositories.ClearRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClearViewModel (application : Application) : AndroidViewModel(application) {

    private val repository : ClearRepository

    init {
        val clearDAO = MyDatabase.getDatabase(application).clearDAO()
        repository = ClearRepository(clearDAO)
    }

    fun nukeTheBase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.nukeTheBase()
        }
    }

}