package com.example.addresssearch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.addresssearch.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private var mBinding : ActivitySearchBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
        init()
    }

    private fun viewBinding(){
        mBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    private fun init(){
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.addJavascriptInterface(BridgeInterface(), "Android")
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                binding.webView.loadUrl("javascript: sample2_execDaumPostcode();")
            }
        }
        binding.webView.loadUrl("your_server_url")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()) {
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    inner class BridgeInterface{
        fun processDATA(data : String){
            val intent = Intent()
            intent.putExtra("data",data)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}