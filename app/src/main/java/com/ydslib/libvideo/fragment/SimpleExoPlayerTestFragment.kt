package com.ydslib.libvideo.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.ydslib.libvideo.R

class SimpleExoPlayerTestFragment : Fragment() {

    private var mStyledPlayerView: StyledPlayerView? = null

    private var exoPlayer: ExoPlayer? = null

    private var isPlaying: Boolean = false

    private var playIcon: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_simple_exo_player_test, container, false)
        mStyledPlayerView = view.findViewById(R.id.player_view)
        playIcon = view.findViewById(R.id.play_icon)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        play01()
        doubleTapFullScreen()
    }

    private fun play01() {
        exoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        mStyledPlayerView?.player = exoPlayer

        val url = "https://wave.video/embed/6581e9eedd95d26a606f6c87/6581e9eedd95d26a606f6c85.mp4"
        val mediaItem = MediaItem.fromUri(url)
        exoPlayer?.addMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.playWhenReady = true

        mStyledPlayerView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    }

    private fun doubleTapFullScreen() {
        mStyledPlayerView?.setOnTouchListener(object : View.OnTouchListener {
            val gestureDetector = GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent?): Boolean {
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

    private fun singleTap() {
        togglePlay()
        if (isPlaying) {
            setPlayIconAnimIn()
        } else {
            setPlayIconAnimOut()
        }
        isPlaying = !isPlaying
    }

    private fun togglePlay() {
        if (isPlaying) {
            exoPlayer?.pause()
        } else {
            exoPlayer?.play()
        }
    }

    override fun onResume() {
        super.onResume()
        mStyledPlayerView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mStyledPlayerView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        exoPlayer = null
    }

}

