package com.example.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.webview.databinding.ActivityBlogBinding

class BlogActivity : AppCompatActivity() {

    private var mBinding : ActivityBlogBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
        init()
    }

    private fun viewBinding(){
        mBinding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init(){
        val url = "https://m.blog.naver.com/cjy2103"
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url)
        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.webViewClient = WebViewClient()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if((keyCode == KeyEvent.KEYCODE_BACK) && binding.webView.canGoBack()){
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}