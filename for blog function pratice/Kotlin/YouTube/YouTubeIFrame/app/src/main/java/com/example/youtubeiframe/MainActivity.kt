package com.example.youtubeiframe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubeiframe.databinding.ActivityMainBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBarListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var youtubePlayerSeekBar : YouTubePlayerSeekBar
    private lateinit var mYouTubePlayer: YouTubePlayer

    private lateinit var videoId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
        init()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        videoId = "gt-v_YCkaMY";
        youtubeInit()
    }

    private fun youtubeInit(){
        youtubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerSeekBar = binding.youtubePlayerSeekbar


        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                mYouTubePlayer = youTubePlayer
                youTubePlayer.cueVideo(videoId, 0F)
                youTubePlayer.addListener(youtubePlayerSeekBar)
            }
        })

        youtubePlayerSeekBar.youtubePlayerSeekBarListener =
            object : YouTubePlayerSeekBarListener {
                override fun seekTo(time: Float) {
                    mYouTubePlayer.seekTo(time)
                }
            }


    }
}