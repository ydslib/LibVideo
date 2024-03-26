package com.ydslib.libvideo

import android.animation.ObjectAnimator
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView

class PlayerViewDemo02 : AppCompatActivity() {

    private var mStyledPlayerView: StyledPlayerView? = null

    private var exoPlayer: ExoPlayer? = null

    private var isPlaying = false

    private var isFullscreen = false

    private var playIcon: ImageView? = null

    private var seekBar: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_view_demo02)

        mStyledPlayerView = findViewById(R.id.player_view)
        playIcon = findViewById(R.id.play_icon)
        seekBar = findViewById(R.id.seek_bar)

        exoPlayer = ExoPlayer.Builder(this).build()
        mStyledPlayerView?.player = exoPlayer

        val url = "https://wave.video/embed/6581e9eedd95d26a606f6c87/6581e9eedd95d26a606f6c85.mp4"
        val mediaItem = MediaItem.fromUri(url)
        exoPlayer?.addMediaItem(mediaItem)

        exoPlayer?.playWhenReady = true

        exoPlayer?.prepare()

        mStyledPlayerView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

        doubleTapFullScreen()
    }

    private fun doubleTapFullScreen() {
        mStyledPlayerView?.setOnTouchListener(object : OnTouchListener {
            val gestureDetector = GestureDetector(this@PlayerViewDemo02, object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent?): Boolean {
                    toggleFullScreen()
                    return super.onDoubleTap(e)
                }

                override fun onSingleTapUp(e: MotionEvent?): Boolean {
                    singleTap()
                    return super.onSingleTapUp(e)
                }
            })

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                gestureDetector.onTouchEvent(event)
                return true
            }

        })
    }

    private fun singleTap() {
        togglePlay()
        seekBar?.isVisible = !isPlaying
        if (isPlaying) {
            setPlayIconAnimIn()
        } else {
            setPlayIconAnimOut()
        }
        isPlaying = !isPlaying
    }

    private fun toggleFullScreen() {
        if (isFullscreen) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            supportActionBar?.show()
        } else {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            supportActionBar?.hide()
        }
        isFullscreen = !isFullscreen
    }

    private fun setPlayIconAnimIn() {
        if (playIcon == null) return
        val obj = ObjectAnimator.ofFloat(playIcon!!, "alpha", 0f, 1.0f)
        obj.duration = 500
        obj.start()
    }

    private fun setPlayIconAnimOut() {
        if (playIcon == null) return
        val obj = ObjectAnimator.ofFloat(playIcon!!, "alpha", 1.0f, 0f)
        obj.duration = 500
        obj.start()
    }

    override fun onResume() {
        super.onResume()
        mStyledPlayerView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mStyledPlayerView?.onPause()
    }

    private fun togglePlay() {
        if (isPlaying) {
            exoPlayer?.pause()
        } else {
            exoPlayer?.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
    }

}