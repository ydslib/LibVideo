package com.ydslib.libvideo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ydslib.libvideo.R

class StyledPlayerViewTestFragment : Fragment() {

    private var btnSimpleExoPlayer: Button? = null

    private var btnProgressiveMediaSource: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_styled_player_view_test, container, false)
        btnSimpleExoPlayer = view.findViewById(R.id.simple_exo_player_btn)
        btnProgressiveMediaSource = view.findViewById(R.id.progressive_media_source_btn)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSimpleExoPlayer?.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_styledPlayerViewTestFragment_to_simpleExoPlayerTestFragment)
        }

        btnProgressiveMediaSource?.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_styledPlayerViewTestFragment_to_progressiveMediaSourceTestFragment)
        }

    }

}