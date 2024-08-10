package com.alfanlukeyan.suitmediainterntest.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alfanlukeyan.suitmediainterntest.databinding.ActivityThirdScreenBinding
import com.alfanlukeyan.suitmediainterntest.retrofit.OnUserClickListener
import com.alfanlukeyan.suitmediainterntest.retrofit.UserAdapter

class ThirdScreen : AppCompatActivity(), OnUserClickListener {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel: ThirdScreenViewModel by viewModels()

    private lateinit var adapter: UserAdapter
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.getStringExtra("name") ?: "John Doe"

        setupRecyclerView()
        setupObservers()
        setupListeners()

        viewModel.getUsers(1)
    }

    private fun setupRecyclerView() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(mutableListOf(), this)
        binding.rvUsers.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.users.observe(this, Observer { users ->
            if (users != null) {
                adapter.addList(users)
            } else {
                // Handle the case where users is null, if necessary
                Log.e("ThirdScreen", "Received null users from ViewModel")
            }
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
            binding.swipeRefresh.isRefreshing = false
        })

        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = binding.rvUsers.layoutManager?.childCount ?: 0
                val pastVisibleItem = (binding.rvUsers.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val total = adapter.itemCount

                if (total > 0 && !viewModel.isLoading.value!! && pastVisibleItem + visibleItemCount >= total) {
                    viewModel.loadNextPage()
                }
            }
        })
    }


    private fun setupListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshUsers()
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, SecondScreen::class.java).apply {
                putExtra("name", name)
            }
            startActivity(intent)
        }
    }

    override fun onUserItemClicked(position: Int) {
        val intent = Intent(this, SecondScreen::class.java).apply {
            putExtra("name", name)
            putExtra("username", "${adapter.getItem(position).first_name} ${adapter.getItem(position).last_name}")
        }
        startActivity(intent)
    }
}
