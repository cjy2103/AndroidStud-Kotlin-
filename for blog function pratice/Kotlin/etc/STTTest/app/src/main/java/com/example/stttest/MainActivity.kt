package com.example.stttest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.stttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RecognitionListener {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private var isRunning = false

    private lateinit var speechRecognizer : SpeechRecognizer

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == RESULT_OK) {
            val results = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            // 음성 인식 결과 처리
            Toast.makeText(this, results!![0], Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStt.setOnClickListener {
            if(!isPermission()){
                return@setOnClickListener
            }

            if(isPermission()){
                if(isRunning){
                    isRunning = false
                    stopSTT()
                } else {
                    startSTT()
                }

            }
        }
    }

    private fun isPermission(): Boolean {
        val permission = Manifest.permission.RECORD_AUDIO
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun startSTT(){
//        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")
//        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "음성으로 입력하세요.")
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(this)

        speechRecognizer.startListening(intent)

    }

    private fun stopSTT(){
        speechRecognizer.stopListening()
    }

    override fun onReadyForSpeech(params: Bundle?) {

    }

    override fun onBeginningOfSpeech() {

    }

    override fun onRmsChanged(rmsdB: Float) {

    }

    override fun onBufferReceived(buffer: ByteArray?) {

    }

    override fun onEndOfSpeech() {

    }

    override fun onError(error: Int) {
        val resultText = "음성인식 실패 다시 시도하세요"
        binding.tvResult.text = resultText
    }

    override fun onResults(results: Bundle?) {
        Log.v("하이하이0","2222222")
        val resultText = results?.getStringArrayList(RecognizerIntent.EXTRA_RESULTS)?.get(0)
        binding.tvResult.text = resultText
    }

    override fun onPartialResults(partialResults: Bundle?) {

    }

    override fun onEvent(eventType: Int, params: Bundle?) {

    }
}