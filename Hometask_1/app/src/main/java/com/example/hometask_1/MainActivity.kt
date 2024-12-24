package com.example.hometask_1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    private val myAdapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = myAdapter
        fab = findViewById(R.id.fab)

        if (savedInstanceState != null) {
            val savedList = savedInstanceState.getIntegerArrayList("saved_items")
            if (savedList != null) {
                myAdapter.setItems(savedList)
            }
        } else {
            myAdapter.setItems(listOf(1, 2, 3, 4, 5))
        }
        fab.setOnClickListener{
            if(myAdapter.itemCount < 20) {
                myAdapter.addItems(myAdapter.itemCount + 1)
            } else {
                Toast.makeText(this, R.string.alert, LENGTH_LONG).show()
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("saved_items", ArrayList(myAdapter.getItems()))
    }
}