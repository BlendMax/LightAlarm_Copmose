package com.kozak.lightalarm

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.provider.Settings
import com.kozak.lightalarm.Constants.MUSIC_NOTIFICATION_ID


class BatteryStatusSupervisorService : Service() {
    private lateinit var receiver: BatteryStatusChangeReceiver
    private val helper by lazy { NotificationHelper(this) }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        AudioPlayer.setMediaPlayer(this)
        receiver = BatteryStatusChangeReceiver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        this.registerReceiver(receiver, filter)
        startForeground(MUSIC_NOTIFICATION_ID, helper.getNotification())
        return START_STICKY
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        stopForeground(STOP_FOREGROUND_DETACH)
        stopSelf()
        AudioPlayer.stopMusic()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}