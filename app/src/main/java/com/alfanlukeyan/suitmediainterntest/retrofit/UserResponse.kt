package com.alfanlukeyan.suitmediainterntest.retrofit

import com.google.gson.annotations.SerializedName

data class UserResponse (

        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("per_page")
        val per_page: Int? = null,

        @field:SerializedName("total")
        val total: Int? = null,

        @field:SerializedName("total_pages")
        val total_pages: Int? = null,

        @field:SerializedName("data")
        val data: ArrayList<Data>? = null,

        @field:SerializedName("support")
        val support: Support? = null,

        )

data class Data (

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("email")
        val email: String? = null,

        @field:SerializedName("first_name")
        val first_name: String? = null,

        @field:SerializedName("last_name")
        val last_name: String? = null,

        @field:SerializedName("avatar")
        val avatar: String? = null,

        )

data class Support (

        @field:SerializedName("url")
        val url: String? = null,

        @field:SerializedName("text")
        val text: String? = null,

        )