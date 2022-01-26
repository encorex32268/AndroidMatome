package com.lihan.androidmatome.mainactivity.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    sealed class UIStatus{
        object Empty : UIStatus()
        data class HaveData(val data : ArrayList<String>) : UIStatus()
        data class SearchData(val data : ArrayList<String>) : UIStatus()
    }
    private val _mData = MutableStateFlow<UIStatus>(UIStatus.Empty)
    val mData  = _mData

    fun getData() {
            val datas  = arrayListOf(
                "Notification","Permission","NavigationComponent"
            )
            _mData.value = UIStatus.HaveData(datas)
    }
    fun searchData(string : String,titles : ArrayList<String>){
        var newList = arrayListOf<String>()
        titles.forEach {
            if (it.contains(string)){
                newList.add(it)
            }
        }
        _mData.value = UIStatus.SearchData(newList)
    }

}