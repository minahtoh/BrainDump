package com.example.braindump.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.braindump.model.Dump

@Database(entities = [Dump::class], version = 2, exportSchema = false)
abstract class DumpDatabase: RoomDatabase() {
    abstract fun getDao(): DumpDao

    companion object{
        @Volatile
        private var INSTANCE :DumpDatabase? = null

        fun getDatabase(context: Context): DumpDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DumpDatabase::class.java,
                    "Dump Database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}