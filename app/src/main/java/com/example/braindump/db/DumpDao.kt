package com.example.braindump.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.braindump.model.Dump

@Dao
interface DumpDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDump(dump: Dump)

    @Delete
    suspend fun deleteDump(dump: Dump)

    @Query("SELECT * from Dumps ORDER by date desc")
    fun getDumpList(): LiveData<List<Dump>>

    @Query("SELECT feelings from Dumps ORDER by date")
    fun getFeelings(): LiveData<List<Int>>

    @Query("SELECT date from dumps")
    fun getDates(): LiveData<List<Long>>
}