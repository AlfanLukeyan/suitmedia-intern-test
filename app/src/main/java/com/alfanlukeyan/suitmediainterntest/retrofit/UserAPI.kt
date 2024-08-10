package com.alfanlukeyan.suitmediainterntest.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {

    @GET("users")
    fun getUsers(
        @Query("page") page: Int?,
    ): Call<UserResponse>

}