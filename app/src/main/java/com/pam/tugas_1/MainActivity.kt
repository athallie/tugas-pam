package com.pam.tugas_1

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pam.tugas_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var mhs = Mahasiswa()
        mhs.nama = "Athallah Naufal Rismaputra Awwaliyyah"
        mhs.nim = "225150407111070"
        val mahasiswaList = mutableListOf(mhs)
        for (i in 1..10) {
            val nameString = ('A'..'Z').toMutableList()
            nameString.add(' ')
            nameString.joinToString(separator = "")
            mhs = Mahasiswa()
            mhs.nama = ""
            mhs.nim = ""
            for (j in 1..20) {
                mhs.nama += nameString.random()
            }
            val nimString = (0..9).toList().joinToString(separator = "")
            for (j in 1..15) {
                mhs.nim += nimString.random()
            }
            mahasiswaList.add(mhs)
        }
        val mahasiswaAdapter = MahasiswaAdapter(mahasiswaList)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mahasiswaAdapter

        binding.addButton.setOnClickListener {
            mhs = Mahasiswa()
            mhs.nama = binding.namaEdittext.text.toString();
            mhs.nim = binding.nimEdittext.text.toString()
            binding.namaEdittext.text?.clear()
            binding.nimEdittext.text?.clear()
            mahasiswaList.add(0, mhs)
            mahasiswaAdapter.notifyItemInserted(0)
            recyclerView.smoothScrollToPosition(0)
            binding.root.hideKeyboard(binding.root)
        }

        /*
            Athallah Naufal Rismaputra Awwaliyyah
            225150407111070
        */
    }

    fun View.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}