package com.example.lockscreenex

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("App", "onCreate")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getSystemService(NotificationManager::class.java).run {
                val foreChannel = NotificationChannel(
                    "id",
                    "Foreground",
                    NotificationManager.IMPORTANCE_HIGH,
                )
                createNotificationChannel(foreChannel)
            }
        }
    }
}