package com.ydslib.libvideo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        findViewById<Button>(R.id.player_view_demo01).setOnClickListener {
            val intent = Intent(this,PlayerViewDemo01::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.styled_player).setOnClickListener {
            val intent = Intent(this,PlayerViewDemo02::class.java)
            startActivity(intent)
        }
    }

}