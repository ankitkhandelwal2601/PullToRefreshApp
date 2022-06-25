package com.example.task.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.task.adapter.CustomAdapter
import com.example.task.R
import com.example.task.model.ItemsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var i: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        swipeRefreshLayout = findViewById(R.id.swipeContainer)

        val data = ArrayList<ItemsViewModel>()

        data.add(ItemsViewModel( "$i"))
        i++

        swipeRefreshLayout.setOnRefreshListener{
            data.add(ItemsViewModel( "$i"))
            i++
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    swipeRefreshLayout.isRefreshing = false
                },
                1000,
            )
            val adapter = CustomAdapter(data)
            recyclerview.adapter = adapter
        }
        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter

    }
}


