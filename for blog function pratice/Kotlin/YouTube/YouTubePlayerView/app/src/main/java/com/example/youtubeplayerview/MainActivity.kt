package com.example.youtubeplayerview

import android.os.Bundle
import android.util.Log
import com.example.youtubeplayerview.databinding.ActivityMainBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener

class MainActivity : YouTubeBaseActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var videoId : String
    private var lastYoutubeSeekTime = 0
    private var mYouTubePlayer : YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        initialize()

        youtubeinit()
    }

    override fun onPause() {
        super.onPause()
        if (mYouTubePlayer != null) {
            lastYoutubeSeekTime = mYouTubePlayer!!.currentTimeMillis
        }
    }

    override fun onResume() {
        super.onResume()
        if (mYouTubePlayer != null) {
            mYouTubePlayer!!.cueVideo(videoId, lastYoutubeSeekTime)
        }
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initialize(){
        videoId = "b7kwTlwD1m0"
    }

    private fun youtubeinit(){
        binding.youtubePlayerView.initialize("Test",object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                youTubePlayer : YouTubePlayer?,
                b : Boolean
            ) {
                if(youTubePlayer != null){
                    if (!b) {
                        try {
                            youTubePlayer.cueVideo(videoId, lastYoutubeSeekTime)
                        } catch (e: IllegalStateException) {
                            youtubeinit()
                        }
                    }
                }

                youTubePlayer!!.setShowFullscreenButton(false)
                mYouTubePlayer = youTubePlayer
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)

                youtubeListener(youTubePlayer)

                youtubeEventListener(youTubePlayer)
            }

            override fun onInitializationFailure(
                provider : YouTubePlayer.Provider?,
                youTubeInitializationResult : YouTubeInitializationResult?
            ) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun youtubeListener(youTubePlayer: YouTubePlayer){
        youTubePlayer.setPlayerStateChangeListener(object : PlayerStateChangeListener {
            override fun onLoading() {}
            override fun onLoaded(s: String) {}
            override fun onAdStarted() {}
            override fun onVideoStarted() {}
            override fun onVideoEnded() {}
            override fun onError(errorReason: YouTubePlayer.ErrorReason) {}
        })
    }

    private fun youtubeEventListener(youTubePlayer: YouTubePlayer){
        youTubePlayer.setPlaybackEventListener(object : PlaybackEventListener {
            override fun onPlaying() {}
            override fun onPaused() {}
            override fun onStopped() {}
            override fun onBuffering(b: Boolean) {}
            override fun onSeekTo(i: Int) {}
        })
    }
}