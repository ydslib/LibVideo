package com.ydslib.libvideo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class PlayerViewDemo02 : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_view_demo02)

        supportActionBar?.hide()

        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController!!)

//        doubleTapFullScreen()
//
//        seekBar?.progress = exoPlayer?.currentPosition?.toInt() ?: 0
//        seekBar?.max = exoPlayer?.duration?.toInt() ?: 0
//
//        timer.schedule(object : TimerTask() {
//            override fun run() {
//                runOnUiThread {
//                    seekBar?.progress = exoPlayer?.currentPosition?.toInt() ?: 0
//                    println("progress:${seekBar?.progress}")
//                }
//            }
//        }, 0, 1000)

//        play02()
    }

//
//    private fun play01() {
//        exoPlayer = ExoPlayer.Builder(this).build()
////        exoPlayer = SimpleExoPlayer.Builder(this).build()
//        mStyledPlayerView?.player = exoPlayer
//
//        val url = "https://wave.video/embed/6581e9eedd95d26a606f6c87/6581e9eedd95d26a606f6c85.mp4"
//        val mediaItem = MediaItem.fromUri(url)
//        exoPlayer?.addMediaItem(mediaItem)
//        exoPlayer?.prepare()
//        exoPlayer?.playWhenReady = true
//
//        mStyledPlayerView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
//    }

//    private fun play02() {
//        exoPlayer = ExoPlayer.Builder(this).build()
//        mStyledPlayerView?.player = exoPlayer
//
//        val url = "https://wave.video/embed/6581e9eedd95d26a606f6c87/6581e9eedd95d26a606f6c85.mp4"
//        //视频来自于网络的，使用DefaultHttpDataSource，非网络的DefaultDataSource
//        val dataSourceFactory = DefaultHttpDataSource.Factory()
//        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(url))
//        exoPlayer?.setMediaSource(mediaSource)
//        exoPlayer?.prepare()
//
//        mediaSource.addEventListener(mHandler, object : MediaSourceEventListener {
//            override fun onLoadCompleted(
//                windowIndex: Int,
//                mediaPeriodId: MediaSource.MediaPeriodId?,
//                loadEventInfo: LoadEventInfo,
//                mediaLoadData: MediaLoadData
//            ) {
//                super.onLoadCompleted(windowIndex, mediaPeriodId, loadEventInfo, mediaLoadData)
//                mediaSource.removeEventListener(this)
//            }
//        })
//    }

}