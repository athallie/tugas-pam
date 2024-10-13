package com.pam.tugas_1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mahasiswa(
    @PrimaryKey val nim: String,
    @ColumnInfo(name = "nama") val nama: String?
)