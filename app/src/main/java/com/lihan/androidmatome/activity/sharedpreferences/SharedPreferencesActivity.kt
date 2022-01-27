package com.lihan.androidmatome.activity.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.lihan.androidmatome.R
import com.lihan.androidmatome.databinding.ActivitySharedPreferencesBinding

class SharedPreferencesActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySharedPreferencesBinding
    private val SPNAME = "myData"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        binding.apply {
            spSaveButton.setOnClickListener {
                val name = spNameEditTextView.text.toString()
                val age = spAgeEditTextView.text.toString().toInt() // type:numberSigned
                setStringSharedPreferences("name",name)
                setIntSharedPreferences("age",age)
            }
            spReadButton.setOnClickListener {
                getData()
            }
        }
    }

    private fun getData() {
        binding.apply {
            spProgressBar.isVisible = true
            spNameEditTextView.setText(getStringSharedPreferences("name"))
            spAgeEditTextView.setText(getIntSharedPreferences("age").toString())
            spProgressBar.isVisible = false
        }
    }

    private fun getStringSharedPreferences(key : String) : String{
        return getSharedPreferences(SPNAME, MODE_PRIVATE).getString(key,"unknown")!!
    }
    private fun getIntSharedPreferences(key : String) : Int {
        return getSharedPreferences(SPNAME, MODE_PRIVATE).getInt(key,0)
    }

    private fun setStringSharedPreferences(key : String,value : String){
        getSharedPreferences(SPNAME, MODE_PRIVATE).edit().putString(key,value).apply()

    }
    private fun setIntSharedPreferences(key : String,value : Int){
        getSharedPreferences(SPNAME, MODE_PRIVATE).edit().putInt(key,value).apply()
    }


}