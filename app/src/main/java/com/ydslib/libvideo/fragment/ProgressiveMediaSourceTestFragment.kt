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
import com.google.android.exoplayer2.source.LoadEventInfo
import com.google.android.exoplayer2.source.MediaLoadData
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MediaSourceEventListener
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.ydslib.libvideo.R

class ProgressiveMediaSourceTestFragment : Fragment() {
    private var mStyledPlayerView: StyledPlayerView? = null

    private var exoPlayer: ExoPlayer? = null

    private var isPlaying: Boolean = false

    private var playIcon: ImageView? = null

    private val mHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_progressive_media_source_test, container, false)
        mStyledPlayerView = view.findViewById(R.id.player_view)
        playIcon = view.findViewById(R.id.play_icon)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        play02()
        doubleTapFullScreen()
    }

    private fun play02() {
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        mStyledPlayerView?.player = exoPlayer

        val url = "https://wave.video/embed/6581e9eedd95d26a606f6c87/6581e9eedd95d26a606f6c85.mp4"
        //视频来自于网络的，使用DefaultHttpDataSource，非网络的DefaultDataSource
        val dataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(url))
        exoPlayer?.setMediaSource(mediaSource)
        exoPlayer?.prepare()

        mediaSource.addEventListener(mHandler, object : MediaSourceEventListener {
            override fun onLoadCompleted(
                windowIndex: Int,
                mediaPeriodId: MediaSource.MediaPeriodId?,
                loadEventInfo: LoadEventInfo,
                mediaLoadData: MediaLoadData
            ) {
                super.onLoadCompleted(windowIndex, mediaPeriodId, loadEventInfo, mediaLoadData)
                mediaSource.removeEventListener(this)
            }
        })
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