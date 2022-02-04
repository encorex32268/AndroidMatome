package com.lihan.androidmatome.other

import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.Log(string : String){
    android.util.Log.d(this.javaClass.simpleName, "$string")
}
