package com.lihan.androidmatome.activity.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.lihan.androidmatome.R
import com.lihan.androidmatome.databinding.ActivityPermissionsBinding

class PermissionsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPermissionsBinding
    private val CAMERA_REQUEST_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Camera
        binding.apply {
            val isGetCameraPermission =ContextCompat.checkSelfPermission(
                this@PermissionsActivity,
                Manifest.permission.CAMERA
                )
            if (isGetCameraPermission == PackageManager.PERMISSION_GRANTED){
                //TODO OPEN CAMERA
                permissionsStatusTextView.text = "GRANTED"
            }else{
                permissionsStatusTextView.text = "DENIED"
            }
            permissionsGetButton.setOnClickListener {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                   requestPermissions(
                       arrayOf(Manifest.permission.CAMERA),CAMERA_REQUEST_CODE
                   )
               }else{
                   permissionsStatusTextView.text = "API NOT SUPPORT"
               }
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode){
            CAMERA_REQUEST_CODE->{
                if (grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED
                        ){
                    binding.permissionsStatusTextView.text = "GRANTED"
                }else{
                    binding.permissionsStatusTextView.text = "Fail"
                }
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)




    }
}