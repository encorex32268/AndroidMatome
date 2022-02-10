package com.lihan.androidmatome.activity.main

import androidx.lifecycle.ViewModel
import com.lihan.androidmatome.activity.customview.CustomViewActivity
import com.lihan.androidmatome.activity.notification.NotificationActivity
import com.lihan.androidmatome.activity.permissions.PermissionsActivity
import com.lihan.androidmatome.activity.retrofitapi.RetroFitApiActivity
import com.lihan.androidmatome.activity.sharedpreferences.SharedPreferencesActivity
import com.lihan.androidmatome.model.Function
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    sealed class UIStatus{
        object Empty : UIStatus()
        data class HaveData(val data : ArrayList<Function>) : UIStatus()
        data class SearchData(val data : ArrayList<Function>) : UIStatus()
    }
    private val _mData = MutableStateFlow<UIStatus>(UIStatus.Empty)
    val mData  = _mData

    init {
        getData()
    }
    fun getData() {
            val datas  = arrayListOf(
                Function(NotificationActivity::class.java),
                Function(SharedPreferencesActivity::class.java),
                Function(RetroFitApiActivity::class.java),
                Function(PermissionsActivity::class.java),
                Function(CustomViewActivity::class.java)
            )
        _mData.value = UIStatus.HaveData(datas)
    }
    fun searchData(string : String,titles : ArrayList<Function>){
        val newList = arrayListOf<Function>()
        titles.forEach {
            if (it.toFunctionClass.simpleName.contains(string)){
                newList.add(it)
            }
        }
        _mData.value = UIStatus.SearchData(newList)
    }

}