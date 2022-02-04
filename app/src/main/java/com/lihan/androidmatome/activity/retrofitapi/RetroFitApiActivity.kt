package com.lihan.androidmatome.activity.retrofitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lihan.androidmatome.R
import com.lihan.androidmatome.activity.retrofitapi.model.RetrofitItemClick
import com.lihan.androidmatome.activity.retrofitapi.model.User
import com.lihan.androidmatome.activity.retrofitapi.model.UserAdapter
import com.lihan.androidmatome.activity.retrofitapi.model.UserService
import com.lihan.androidmatome.activity.retrofitapi.ui.UserViewModel
import com.lihan.androidmatome.databinding.ActivityRetroFitApiBinding
import com.lihan.androidmatome.other.Log
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Retrofit




class RetroFitApiActivity : AppCompatActivity(), RetrofitItemClick {
    private lateinit var binding : ActivityRetroFitApiBinding
    private val viewModel : UserViewModel by viewModels()
    private lateinit var mAdapter : UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetroFitApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            mAdapter = UserAdapter(arrayListOf()).also {
                it.retrofitItemClick = this@RetroFitApiActivity
            }
            retrofitUserRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@RetroFitApiActivity)
                adapter = mAdapter
            }

            lifecycleScope.launch {
                viewModel.userData.collect {
                    when(it){
                        is UserViewModel.Resource.Loading->{
                            retroFitProgressBar.isVisible = true
                            retrofitStatusText.text = ""
                        }
                        is UserViewModel.Resource.Error->{
                            retroFitProgressBar.isVisible = false
                            retrofitStatusText.text = "Error ${it.message}"
                            retrofitStatusText.setOnClickListener {
                                viewModel.getData()
                            }
                        }
                        is UserViewModel.Resource.Success->{
                            retroFitProgressBar.isVisible = false
                            retrofitStatusText.text = ""
                            mAdapter.users = it.data
                            mAdapter.notifyDataSetChanged()
                        }
                    }
                }

            }





        }



    }

    override fun itemClick(user: User) {
        AlertDialog.Builder(this)
            .setTitle(user.name)
            .setMessage(user.toString())
            .setPositiveButton("Ok",null)
            .show()
    }
}