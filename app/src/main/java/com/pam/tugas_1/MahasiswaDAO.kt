package com.pam.tugas_1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MahasiswaDAO {
    @Query("SELECT * FROM mahasiswa")
    suspend fun getAll(): List<Mahasiswa>
    @Insert
    suspend fun insertAll(vararg mahasiswas: Mahasiswa)
    @Delete
    suspend fun delete(mahasiswa: Mahasiswa)
}