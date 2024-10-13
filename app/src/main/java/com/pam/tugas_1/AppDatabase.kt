package com.pam.tugas_1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mahasiswa::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mahasiswaDao(): MahasiswaDAO
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mahasiswadb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}