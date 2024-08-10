package com.alfanlukeyan.suitmediainterntest.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alfanlukeyan.suitmediainterntest.retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreenViewModel : ViewModel() {

    private val _users = MutableLiveData<List<Data>>()
    val users: LiveData<List<Data>> get() = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _totalPages = MutableLiveData<Int>()
    val totalPages: LiveData<Int> get() = _totalPages

    private var currentPage = 1

    init {
        // Initialize the LiveData with empty lists
        _users.value = emptyList()
        _totalPages.value = 1
    }

    fun getUsers(page: Int, isOnRefresh: Boolean = false) {
        _isLoading.value = true
        val retro = Retrofit.getRetroData().create(UserAPI::class.java)
        retro.getUsers(page).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _totalPages.value = response.body()?.total_pages ?: 1
                    val listResponse = response.body()?.data ?: emptyList()
                    _users.value = if (isOnRefresh) listResponse else _users.value?.plus(listResponse)
                } else {
                    // Handle the case where the response is not successful
                    _users.value = emptyList()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                // Handle the error, possibly using another LiveData or a Snackbar/Toast
                _users.value = emptyList() // Optional: Clear the list on failure
                Log.e("ThirdScreenViewModel", "Failed to fetch users", t)
            }
        })
    }

    fun refreshUsers() {
        currentPage = 1
        getUsers(currentPage, isOnRefresh = true)
    }

    fun loadNextPage() {
        if (currentPage < (totalPages.value ?: 1) && !(isLoading.value ?: false)) {
            currentPage++
            getUsers(currentPage)
        }
    }
}
