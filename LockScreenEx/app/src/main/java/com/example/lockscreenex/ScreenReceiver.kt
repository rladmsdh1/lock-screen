package com.example.lockscreenex

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.lockscreenex.ScreenService.Companion.isFirstNotification


class ScreenReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TAG", "onReceive: ${intent.action}")
        val serviceIntent = Intent(context, ScreenService::class.java)
        if (!isFirstNotification) {
            context.startForegroundService(serviceIntent)
        }
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }
        }
        val lockScreenIntent = Intent(context, LockScreenActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        context.startActivity(lockScreenIntent)
    }
}