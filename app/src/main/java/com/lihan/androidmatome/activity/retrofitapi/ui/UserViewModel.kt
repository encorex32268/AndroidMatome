package com.lihan.androidmatome.activity.retrofitapi.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.androidmatome.activity.retrofitapi.model.User
import com.lihan.androidmatome.activity.retrofitapi.model.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel : ViewModel() {

    companion object{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://user-api-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service  = retrofit.create(UserService::class.java)
    }

    sealed class Resource(val data : List<User>, val message : String? =null){
        class Success(data:  List<User>) : Resource(data)
        class Error(data:  List<User>,error: String) : Resource(data,error)
        object Loading : Resource(arrayListOf())
    }

    private val _userData = MutableStateFlow<Resource>(Resource.Loading)
    var userData = _userData

    init {
        getData()
    }


    fun getData(){
        _userData.value = Resource.Loading
        viewModelScope.launch {
            service.getUserByCount(10).enqueue(object : Callback<List<User>>{
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    val data = response.body()!!
                    _userData.value = Resource.Success(data)
                }
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    _userData.value = Resource.Error(arrayListOf(),t.message.toString())
                }
            })
        }
    }


}