package com.lihan.androidmatome.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lihan.androidmatome.databinding.ActivityMainBinding
import com.lihan.androidmatome.model.Function
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity(), MainAdapter.MainItemClickListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var mAdapter : MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        mAdapter = MainAdapter(arrayListOf()).also {
            it.mainItemClickListener = this
        }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        lifecycleScope.launchWhenStarted {
            viewModel.mData.collect {
                when(it){
                    is MainViewModel.UIStatus.Empty->{}
                    is MainViewModel.UIStatus.HaveData->{
                        mAdapter.titles = it.data
                        mAdapter.notifyDataSetChanged()
                    }
                    is MainViewModel.UIStatus.SearchData->{
                        mAdapter.titles = it.data
                        mAdapter.notifyDataSetChanged()
                    }
                }

            }
        }

        binding.apply {
            mainRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter  = mAdapter
            }
            mainSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    viewModel.searchData(p0?:"",mAdapter.titles)
                    return true
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0!!.isNotEmpty()){
                        viewModel.searchData(p0,mAdapter.titles)
                    }else{
                        viewModel.getData()
                    }
                    return true
                }

            })
        }
    }

    override fun itemClick(function: Function)=
        startActivity(
            Intent(this@MainActivity,function.toFunctionClass)
        )


}