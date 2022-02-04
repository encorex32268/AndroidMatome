package com.lihan.androidmatome.activity.retrofitapi.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("user")
    fun getAllUser() : Call<List<User>>

    @GET("user/count/{count}")
    fun getUserByCount(@Path("count") count : Int):Call<List<User>>
}