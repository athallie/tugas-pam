package com.pam.tugas_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.pam.tugas_1.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mahasiswaAdapter = MahasiswaAdapter(emptyList())
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mahasiswaAdapter

        val mahasiswaDao = AppDatabase.getDatabase(applicationContext).mahasiswaDao()
        lifecycleScope.launch {
            mahasiswaAdapter.updateMahasiswa(mahasiswaDao.getAll())
        }
        /*
            Athallah Naufal Rismaputra Awwaliyyah
            225150407111070
        */

        val addMahasiswa = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.let { data ->
                        val mhs = Mahasiswa(
                            data.getStringExtra("nim").toString(),
                            data.getStringExtra("nama")
                        )
                        mhs.nama?.let { Log.d("TAG", it) }
                         lifecycleScope.launch{
                             mahasiswaDao.insertAll(mhs)
                             mahasiswaAdapter.updateMahasiswa(mahasiswaDao.getAll())
                         }
                        recyclerView.smoothScrollToPosition(0)
                    }
                }
        }
        binding.bottomNavigation.setOnItemSelectedListener {
            addMahasiswa.launch(
                Intent(this, AddMahasiswa::class.java).apply {
                    action = Intent.ACTION_SEND
                }
            )
            true
        }
    }
}