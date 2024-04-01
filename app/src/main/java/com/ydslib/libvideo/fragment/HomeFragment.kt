package com.ydslib.libvideo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ydslib.libvideo.R

class HomeFragment : Fragment() {

    private var btnStyledPlayerView: Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        btnStyledPlayerView = view.findViewById(R.id.styled_player_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnStyledPlayerView?.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_homeFragment_to_styledPlayerViewTestFragment)
        }
    }

}