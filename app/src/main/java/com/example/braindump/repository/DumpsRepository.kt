package com.example.braindump.repository

import androidx.lifecycle.LiveData
import com.example.braindump.db.DumpDatabase
import com.example.braindump.model.Dump

class DumpsRepository( private val db : DumpDatabase) {
    suspend fun saveDump(dump: Dump){
        db.getDao().addDump(dump)
    }
    suspend fun deleteDump(dump: Dump){
        db.getDao().deleteDump(dump)
    }
    fun getDumpList(): LiveData<List<Dump>> {
       return db.getDao().getDumpList()
    }
    fun getFeelingsHistory() = db.getDao().getFeelings()
    fun getDatesHistory() = db.getDao().getDates()
}