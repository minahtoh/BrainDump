package com.example.braindump.viewmodel

import androidx.lifecycle.*
import com.example.braindump.model.Dump
import com.example.braindump.repository.DumpsRepository
import kotlinx.coroutines.launch

class DumpsViewmodel(private val repository: DumpsRepository):ViewModel() {



    fun saveDump(dump: Dump){
        viewModelScope.launch {
            repository.saveDump(dump)
        }
    }
    fun deleteDump(dump: Dump){
        viewModelScope.launch {
            repository.deleteDump(dump)
        }
    }
    fun displayDumpList():LiveData<List<Dump>>{
     return   repository.getDumpList()
    }
    fun feelingsHistory() = repository.getFeelingsHistory()
    fun datesHistory() = repository.getDatesHistory()
}





class DumpsViewModelFactory(private val repository: DumpsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DumpsViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DumpsViewmodel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}