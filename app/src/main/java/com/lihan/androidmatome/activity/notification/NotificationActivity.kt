package com.lihan.androidmatome.activity.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.lihan.androidmatome.R
import com.lihan.androidmatome.databinding.ActivityNotificationBinding
import kotlin.random.Random

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            notificationSendButton.setOnClickListener {
                sendNotification()
            }

        }

    }

    private fun sendNotification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channelName = "Channel Matome"
        val channelID = "Channel ID "

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.apply {
                description = "description .. "
                enableVibration(true)
            }
            manager.createNotificationChannel(notificationChannel)
        }
        val notification = NotificationCompat.Builder(this,channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setWhen(System.currentTimeMillis())
            .setContentTitle("ContentTitle")
            .setContentText("ContentText")
            .setSubText("SubText")
            .build()
        manager.notify(Random.nextInt(),notification)

    }
}