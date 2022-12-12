package com.kozak.lightalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BatteryStatusChangeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        when(intent?.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                AudioPlayer.playMusic(context)

            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                AudioPlayer.stopMusic()


            }
        }
    }
}