package com.example.exoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.RawResourceDataSource

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()
    }

    override fun onStart() {
        super.onStart()
        SystemUtil.sofNavigationBarHide(window)
        SystemUtil.statusbarSetting(window)
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        exoPlayer = ExoPlayer.Builder(this).build()

        val mediaItem = MediaItem.fromUri(
            RawResourceDataSource.buildRawResourceUri(R.raw.fhd)
        )
        // 실행할 비디오 설정
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        // 영상이 종료되면 자동 반복
        exoPlayer.repeatMode = ExoPlayer.REPEAT_MODE_ALL

        // 초기 설정한 video 크기만큼 강제로 사이즈 늘림
        binding.exoplayer.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

        exoPlayer.play()

        binding.exoplayer.player = exoPlayer


    }
}