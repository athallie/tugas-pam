package com.pam.tugas_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MahasiswaAdapter(private val dataSet: MutableList<Mahasiswa>) : RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.mahasiswa_row_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nama.text = dataSet[position].nama
        holder.nim.text = dataSet[position].nim
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama: TextView
        val nim: TextView
        init {
            nama = view.findViewById(R.id.nama_textview)
            nim = view.findViewById(R.id.nim_textview)
        }
    }
}