package com.ydslib.libvideo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class PlayerViewDemo01 : AppCompatActivity() {


    private var mPlayerView: PlayerView? = null
    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_view_demo01)

        mPlayerView = findViewById(R.id.player_view)
        initVideoOne()
    }

    private fun initVideoOne(){
        player = SimpleExoPlayer.Builder(this).build()

        mPlayerView?.player = player

        val url = "https://wave.video/embed/6581e9eedd95d26a606f6c87/6581e9eedd95d26a606f6c85.mp4"
        val mediaItem = MediaItem.fromUri(url)

        player?.addMediaItem(mediaItem)
        player?.prepare()
    }

    override fun onResume() {
        super.onResume()
        mPlayerView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPlayerView?.onPause()
    }
}