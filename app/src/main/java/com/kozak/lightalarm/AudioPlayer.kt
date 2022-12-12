package com.kozak.lightalarm

import android.content.Context
import android.media.MediaPlayer
import android.provider.Settings

object AudioPlayer {
    private lateinit var mediaPlayer: MediaPlayer

    fun setMediaPlayer(context: Context){
        mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI)
    }

    fun playMusic(context: Context){
        if(mediaPlayer.isPlaying){
            mediaPlayer.start()
        }else{
            setMediaPlayer(context)
            mediaPlayer.start()

        }
    }

    fun stopMusic(){
        mediaPlayer.stop()
    }
}