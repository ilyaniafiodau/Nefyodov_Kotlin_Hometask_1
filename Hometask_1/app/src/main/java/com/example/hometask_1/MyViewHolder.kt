package com.example.hometask_1

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val txt = view.findViewById<TextView>(R.id.text)
    val image = view.findViewById<ImageView>(R.id.image_view)

    fun bind(number: Int) {
        txt.text = "$number"
    }
}