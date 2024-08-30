package com.example.lockscreenex

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class ScreenService : Service() {
    private var mReceiver: ScreenReceiver? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        Log.d("TAG", "onStartCommand${this@ScreenService.hashCode()}")
        createNotification()
        mReceiver = ScreenReceiver()
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        registerReceiver(mReceiver, filter)
        restartService()
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification() {
        val i = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            i,
            PendingIntent.FLAG_IMMUTABLE

        )
        val notificationBuilder = NotificationCompat.Builder(this, "id")
            .setContentTitle("foreground")
            .setContentText("ㅁㄴㅇ")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setDeleteIntent(
                createOnDismissedIntent(
                    context = this@ScreenService,
                    notificationId = 1
                )
            )
            .setOnlyAlertOnce(true)
        startForeground(1, notificationBuilder.build())
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun restartService() {
        val restartIntent = Intent(applicationContext, ScreenService::class.java)
        val pendingRestartIntent = PendingIntent.getService(
            this,
            1,
            restartIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT
        )

        // 10분 후 서비스 재시작 시도
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME,
            SystemClock.elapsedRealtime() + (1000 * 20),
            pendingRestartIntent
        )
    }

    private fun createOnDismissedIntent(context: Context, notificationId: Int): PendingIntent {
        Log.d("TAG", "createOnDismissedIntent")
        val intent = Intent(
            context,
            ScreenReceiver::class.java
        )
        intent.putExtra("com.my.app.notificationId", notificationId)

        val pendingIntent =
            PendingIntent.getBroadcast(
                context.applicationContext,
                notificationId, intent, PendingIntent.FLAG_IMMUTABLE
            )
        return pendingIntent
    }
}